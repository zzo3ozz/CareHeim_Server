package api.model.response;

import org.json.simple.JSONObject;

import clothe.model.FeaturedClothe;

public class ClotheInfoResForMobile {
	public static final int UNSAVED = 0;
	public static final int SAVED = 1;
	public static final int HAVE_CAREINFO = 2;
	public static final int SUCCESS = 3;
	
	private int status;
	private FeaturedClothe featuredClothe;
	
	public ClotheInfoResForMobile(int status, FeaturedClothe featuredClothe) {
		this.status = status;
		this.featuredClothe = featuredClothe;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public FeaturedClothe getFeaturedClothe() {
		return this.featuredClothe;
	}
	
	public JSONObject buildToJSON() {
		JSONObject result = new JSONObject();
		
		result.put("status", this.status);
		
		JSONObject clothe = new JSONObject();
		
		clothe.put("type", this.featuredClothe.getType());
		clothe.put("ptn", this.featuredClothe.getPtn());
		clothe.put("colors", this.featuredClothe.getColors());
		clothe.put("features", this.featuredClothe.getFeatures());
		
		result.put("clothe", clothe);
		
		return result;
	}
}
