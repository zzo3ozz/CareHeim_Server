package api;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import api.model.response.*;

public class ResponseProvider {
	public static String buildJSON(Response response) {
		JSONObject result = new JSONObject();
		
		result.put("responseType", response.getResponseType());
		result.put("user", response.getUser());
		result.put("body", response.buildBodyToJSON());
		
		return result.toJSONString();
	}
}
