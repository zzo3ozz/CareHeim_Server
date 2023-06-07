package api.model.response;

import org.json.simple.JSONObject;

public class DBResultRes {
	private boolean isSuccess;
	
	public DBResultRes(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public boolean getSuccess() {
		return this.isSuccess;
	}
	
	public JSONObject buildToJSON() {
		JSONObject result = new JSONObject();
		
		result.put("success", this.isSuccess);
		
		return result;
	}
}
