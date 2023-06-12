package api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import api.model.request.*;
import api.model.response.*;
import clothe.ClotheController;
import clothe.ClotheDAO;
import clothe.ClotheProvider;
import clothe.model.*;
import image.ImageController;

public class RequestProvider {	
	public static JSONParser parser = new JSONParser();
	
	public static Request parsing(String message) {
		// JSON 파싱
        try {
			JSONObject object = (JSONObject) parser.parse(message);
			
			int deviceType = Long.valueOf(Optional.ofNullable((Long) object.get("device")).orElse(0L)).intValue(); 
			int requestType = Long.valueOf(Optional.ofNullable((Long) object.get("requestType")).orElse(0L)).intValue();
			String user = (String) object.get("user");
			JSONObject body = (JSONObject) object.get("body");
			
			return new Request(deviceType, requestType, user, body);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.print("파싱 실패");
			return null;
		}
	}
	
	public static RaspberryRequestBody parsingRPBody(JSONObject body) {
		RaspberryRequestBody parsed = new RaspberryRequestBody();
		if(body != null) {
			if(body.get("img") != null) {
				String img_string = (String) body.get("img");
				parsed.setFilePath(img_string);
				
				String file_path = "C:/Users/hhzzo/conda/careheim/seg_result/" + img_string + ".png";
				File file = new File(file_path);
		    	
		    	FileInputStream fis;
				try {
					fis = new FileInputStream(file);
					byte img[] = new byte[(int)file.length()];
					fis.read(img);
					parsed.setImg(img);
					System.out.println("images set");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(body.get("extraFeatures") != null) {
				parsed.setExtraFeatures((String[]) body.get("extraFeatures"));
			}
			
			if(body.get("savingIndex") != null) {
				parsed.setSavingIndex(Long.valueOf(Optional.ofNullable((Long) body.get("savingIndex")).orElse(0L)).intValue());
			}
		}
		System.out.println("body parsing success");
		
		return parsed;
	}
		
	public static MobileRequestBody parsingMBBody(JSONObject body) {
		MobileRequestBody parsed = new MobileRequestBody();
		
		if(body != null) {			
			if(body.get("careInfos") != null) {
				parsed.setCareInfos((String[]) body.get("careInfos"));
			}
			
			if(body.get("clothe") != null) {
				JSONObject clothe = (JSONObject) body.get("clothe");
				FeaturedClothe featuredClothe = new FeaturedClothe();
				
				featuredClothe.setType(Long.valueOf(Optional.ofNullable((Long) body.get("type")).orElse(0L)).intValue());
				featuredClothe.setPtn(Long.valueOf(Optional.ofNullable((Long) body.get("ptn")).orElse(0L)).intValue());
				featuredClothe.setColors((String[]) body.get("colors"));
				featuredClothe.setFeatures((String[]) body.get("features"));
				parsed.setFeaturedClothe(null);
			}
		}
		System.out.println("body parsing success");
		return parsed;
	}

	public static ClotheInfoRes[] getClotheInfos(String user, String filePath, byte[] img) throws IOException {
		System.out.println("get Clothe Infos");
		ArrayList<SegmentResult> clothe_imgs = ImageController.getClotheSegmentation(filePath, img);
		
		ClotheInfoRes[] clotheInfoReses = new ClotheInfoRes[clothe_imgs.size()];
		
		for(int i = 0; i < clothe_imgs.size(); i++) {
			Clothe clothe = ClotheController.extractFeature(clothe_imgs.get(i));
			ClotheDocument result = null;
			
			int result_status = ClotheController.searchForOneClothe(user, clothe, result);
			
			if(result_status == ClotheController.SUCCESS) {
				clotheInfoReses[i] = new ClotheInfoRes(true, result);
			} else {
				clotheInfoReses[i] = new ClotheInfoRes(false, (FeaturedClothe)clothe);
			}
		}
		
		return clotheInfoReses;
	}
	
	public static CareInfoRes[] getCareInfos(String user, String filePath, byte[] img) throws IOException {
		ArrayList<SegmentResult> clothe_imgs = ImageController.getClotheSegmentation(filePath, img);
		
		CareInfoRes[] careInfoReses = new CareInfoRes[clothe_imgs.size()];
		
		for(int i = 0; i < clothe_imgs.size(); i++) {			
			Clothe clothe = ClotheController.extractFeature(clothe_imgs.get(i));
			
			ClotheDocument result = null;
			
			int status = ClotheController.searchForOneClothe(user, clothe, result);
			
			boolean isSaved = true;
			
			if(status == ClotheController.NONEXIST_DB) {
				isSaved = false;
				result = new ClotheDocument(clothe);
			}
			
			CareInfo careInfo = new CareInfo(result);
			
			if(ClotheProvider.canDetectStain(careInfo.getPtn())) {
				careInfo.setCanDetectStain(true);
				careInfo.setHasStain(ClotheProvider.getStain(clothe.getImg()));
			} else {
				careInfo.setCanDetectStain(false);
				careInfo.setHasStain(false);
			}
			
			careInfoReses[i] = new CareInfoRes(isSaved, careInfo);
		}
		
		return careInfoReses;
	}
	
	public static ClotheInfoResForMobile getRecentEnroll(String user) {
		ClotheDocument document = ClotheDAO.selectRecentClothe(user);
		ClotheInfoResForMobile response;
		
		if(document != null) {
			if(document.getCareInfos() != null) {
				response = new ClotheInfoResForMobile(ClotheInfoResForMobile.SUCCESS, document);
			} else {
				response = new ClotheInfoResForMobile(ClotheInfoResForMobile.HAVE_CAREINFO, null);
			}
		} else {
			response = new ClotheInfoResForMobile(ClotheInfoResForMobile.UNSAVED, null);
		}
		
		return response;
	}
	
	public static ClotheInfoResForMobile getSearchedClothe(String user, FeaturedClothe clothe) {
		ClotheDocument[] documents = ClotheDAO.selectClothe(user, clothe.getType(), clothe.getPtn(), clothe.getColors(), clothe.getFeatures());
		ClotheInfoResForMobile response;
		
		if(documents.length >= 2) {
			response = new ClotheInfoResForMobile(ClotheInfoResForMobile.SAVED, null);
		} else if(documents.length < 1) {
			response = new ClotheInfoResForMobile(ClotheInfoResForMobile.UNSAVED, null);
		} else {
			response = new ClotheInfoResForMobile(ClotheInfoResForMobile.SUCCESS, documents[0]);
		}
		
		return response;
	}
	
	public static DBResultRes insertClothe(String user, int savingIndex, String[] extraFeatures, ClotheInfoRes[] response) {
		return new DBResultRes(ClotheDAO.insertClothe(user, response[savingIndex].getFeaturedClothe(), extraFeatures));
	}
	
	public static DBResultRes updateCareInfo(String user, String[] careInfos, ClotheInfoResForMobile response) {
		return new DBResultRes(ClotheDAO.updateCareInfos(user, response.getFeaturedClothe(), careInfos));
	}
}
