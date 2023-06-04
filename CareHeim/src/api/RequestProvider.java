package api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import api.model.request.*;
import clothe.model.Clothe;

public class RequestProvider {
	public static final int RASPBERRY = 0;
	public static final int MOBILE = 1;
	
	public static final int RP_CLOTHE_INFO = 0;
	public static final int RP_SAVE = 1;
	public static final int RP_CARE_INFO = 2;
	public static final int RP_CLOSE = 4;
	
	public static final int MB_INFO_LATEST = 0;
	public static final int MB_INFO = 1;
	public static final int MB_SAVE = 2;
	public static final int MB_CLOSE = 3;
	
	public static JSONParser parser = new JSONParser();
	
	public static Request parsing(String message) {
		// JSON 파싱
        try {
			JSONObject object = (JSONObject) parser.parse(message);
			
			int deviceType = (int) object.get("device");
			int requestType = (int) object.get("requestType");
			JSONObject body = (JSONObject) object.get("body");
			
			return new Request(deviceType, requestType, body);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.print("파싱 실패");
			return null;
		}
	}
	
	public static String parsingRPUser(JSONObject body) {
		return (String) body.get("user");
	}
	
	public static RequestBody parsingRPBody(int requestType, JSONObject body) {
		RequestBody parsed = null;
		
		if(requestType == RP_CLOTHE_INFO || requestType == RP_CARE_INFO) {
			String user = (String) body.get("user");
			String img_string = (String) body.get("img");
			byte[] img = img_string.getBytes(); // utf-8 설정하지 않아도 괜찮은지?
			parsed = new ExtractFeatures(user, img);
		} else if(requestType == RP_SAVE) {
			String user = (String) body.get("user");
			String[] extraFeatures = (String[]) body.get("extraFeatures");
			parsed = new AddExtraFeatures(user, extraFeatures);
		}

		return parsed;
	}
		
	public static Clothe[] clotheInfo(byte[] img) {
		Clothe[] clothes = null;
		
		
		
		
		
		return clothes;
	}
	
	public static Clothe[] careInfo(Clothe[] clothes) {
		
		
		return clothes;
	}
}
