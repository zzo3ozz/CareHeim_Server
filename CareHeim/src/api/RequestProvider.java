package api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
				
				byte[] img;
				try {
					img = img_string.getBytes("UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					img = img_string.getBytes();
				}
				
				parsed.setImg(img);
			}
			
			if(body.get("extraFeatures") != null) {
				parsed.setExtraFeatures((String[]) body.get("extraFeatures"));
			}
			
			if(body.get("savingIndex") != null) {
				parsed.setSavingIndex(Long.valueOf(Optional.ofNullable((Long) body.get("savingIndex")).orElse(0L)).intValue());
			}
		}

		return parsed;
	}
		
	public static MobileRequestBody parsingMBBody(JSONObject body) {
		MobileRequestBody parsed = new MobileRequestBody();
		
		if(body != null) {
			if(body.get("features") != null) {
				parsed.setFeatures((String[]) body.get("Features"));
			}
			
			if(body.get("careInfos") != null) {
				parsed.setCareInfos((String[]) body.get("careInfos"));
			}
		}
		
		return parsed;
	}

	public static ClotheInfoRes[] getClotheInfos(String user, byte[] img) throws IOException {
		ArrayList<SegmentResult> clothe_imgs = ImageController.getClotheSegmentation(img);
		
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
	
	public static CareInfoRes[] getCareInfos(String user, byte[] img) throws IOException {
		ArrayList<SegmentResult> clothe_imgs = ImageController.getClotheSegmentation(img);
		
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
	
	public static DBResultRes insertClothe(String user, int savingIndex, String[] extraFeatures, ClotheInfoRes[] response) {
		return new DBResultRes(ClotheDAO.insertClothe(user, response[savingIndex].getFeaturedClothe(), extraFeatures));
	}
	
	public static DBResultRes updateCareInfo(String user, String[] careInfos, ClotheInfoResForMobile response) {
		return new DBResultRes(ClotheDAO.updateCareInfos(user, response.getFeaturedClothe(), careInfos));
	}
}
