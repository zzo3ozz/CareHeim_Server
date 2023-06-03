package request;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import clothe.model.Clothe;
import request.model.*;

public class RequestProvider {
	public static final int RASPBERRY = 0;
	public static final int MOBILE = 1;

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
	
//	public static Object parsingBody(int device, JSONObject body) {
//		Object content;
//		
//		if(device == 0) {
//			String user = (String) body.get("user");
//			String img = (String) body.get("img");
//			String[] extraFeatures = (String[]) body.get("extraFeatures");
//			content = new RequestBodyRP(user, img, extraFeatures);
//		} else {
//			String user;
//			String[] features;
//			String[] careInfos;
//		}
//		return content;
//	}
	
	public static Clothe[] clotheInfo(String img) {
		Clothe[] clothes = null;
		
		
		
		
		
		return clothes;
	}
	
	public static Clothe[] careInfo(String img) {
		Clothe[] clothes = null;
		
		
		
		
		
		return clothes;
	}
}
