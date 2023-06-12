package api;

import api.model.response.Response;

public class ResponseController {
	public static String buildResponse(Response response) {
		return ResponseProvider.buildJSON(response);
	}
}
