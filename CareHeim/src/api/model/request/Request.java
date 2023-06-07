package api.model.request;

import org.json.simple.JSONObject;

public class Request {
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
	
	private int device;
	private int requestType;
	private String user;
	private JSONObject body;
	
	public Request(int device, int requestType, String user, JSONObject body) {
		this.device = device;
		this.requestType = requestType;
		this.user = user;
		this.body = body;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public int getDevice() {
		return this.device;
	}
	
	public int getRequestType() {
		return this.requestType;
	}
	
	public JSONObject getBody() {
		return this.body;
	}
}
