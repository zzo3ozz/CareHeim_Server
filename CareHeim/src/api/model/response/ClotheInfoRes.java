package api.model.response;

import org.json.simple.JSONObject;

import clothe.model.FeaturedClothe;

public class ClotheInfoRes{
	public static final int UNSAVED = 0;
	public static final int SAVED = 1;
	
	private int status;
	private FeaturedClothe clothe;
	
	public ClotheInfoRes(boolean isSaved, FeaturedClothe clothe) {
		if(isSaved) {
			this.status = SAVED;
		} else {
			this.status = UNSAVED;
		}

		this.clothe = clothe;
	}

	public int getStatus() {
		return this.status;
	}
	
	public FeaturedClothe getFeaturedClothe() {
		return this.clothe;
	}
	
	public JSONObject buildFeaturedClotheToJSON() {
		JSONObject result = new JSONObject();
		
		result.put("type", this.clothe.getType());
		result.put("ptn", this.clothe.getPtn());
		result.put("colors", this.clothe.getColors());
		
		if(clothe.getFeatures() != null) {
			result.put("features", this.clothe.getFeatures());
		}
		
		return result;
	}
}
