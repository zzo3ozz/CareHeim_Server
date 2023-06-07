package api.model.response;

import org.json.simple.JSONObject;

import clothe.model.Clothe;
import clothe.model.CareInfo;

public class CareInfoRes {
	public static final int UNSAVED = 0;
	public static final int SAVED = 1;
	
	private int status;
	private CareInfo careInfo;

	
	public CareInfoRes(boolean isSaved, CareInfo careInfo) {
		if(isSaved) {
			this.status = SAVED;
		} else {
			this.status = UNSAVED;
		}

		this.careInfo = careInfo;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public CareInfo getCareInfo() {
		return this.careInfo;
	}
	
	public JSONObject buildFeaturedClotheToJSON() {
		JSONObject result = new JSONObject();
		
		result.put("type", this.careInfo.getType());
		result.put("ptn", this.careInfo.getPtn());
		result.put("colors", this.careInfo.getColors());
		
		if(this.careInfo.getFeatures() != null) {
			result.put("features", this.careInfo.getFeatures());
		}
		
		if(this.careInfo.getCareInfos() != null) {
			result.put("careInfos", this.careInfo.getCareInfos());
		}
		
		result.put("canDetectStain", this.careInfo.getCanDetectStain());
		result.put("hasStain", this.careInfo.getHasStain());
		
		
		return result;
	}
}
