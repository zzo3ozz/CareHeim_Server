package api.model.response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Response<T> {
	public static final int RETURN_CLOTHE = 0;
	public static final int RETURN_DB_STATE = 1;
	
	private int responseType;
	private String user;
	private T responseBody;
	
	public Response() {
		
	}
	
	public Response(int responseType, String user, T responseBody) {
		this.responseType = responseType;
		this.user = user;
		this.responseBody = responseBody;
	}
	
	public void setReponsType(int responseType) {
		this.responseType = responseType;
	}
	
	public void setResponsBody(T responseBody) {
		this.responseBody = responseBody;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public int getResponseType() {
		return this.responseType;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public T getResponsBody() {
		return this.responseBody;
	}
	
	public JSONObject buildBodyToJSON() {
		JSONObject result = new JSONObject();
		
		if(this.responseBody instanceof CareInfoRes[]) {
			CareInfoRes[] array = (CareInfoRes[]) this.responseBody;
			
			JSONArray statuses = new JSONArray();
			JSONArray clothes = new JSONArray();
			
			for(CareInfoRes item : array) {
				statuses.add(item.getStatus());
				clothes.add(item.buildFeaturedClotheToJSON());
			}
			
			result.put("statuses", statuses);
			result.put("clothes", clothes);
		} else if(this.responseBody instanceof ClotheInfoRes[]) {
			ClotheInfoRes[] array = (ClotheInfoRes[]) this.responseBody;
			
			JSONArray statuses = new JSONArray();
			JSONArray clothes = new JSONArray();
			
			for(ClotheInfoRes item : array) {
				statuses.add(item.getStatus());
				clothes.add(item.buildFeaturedClotheToJSON());
			}
			
			result.put("statuses", statuses);
			result.put("clothes", clothes);
		} else if(this.responseBody instanceof ClotheInfoResForMobile) {
			result = ((ClotheInfoResForMobile) this.responseBody).buildToJSON();
		} else if(this.responseBody instanceof DBResultRes) {
			result = ((DBResultRes) this.responseBody).buildToJSON();
		}
		
		return result;
	}
}
