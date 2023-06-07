package api.model.request;

public class MobileRequestBody {
	private String[] features;
	private String[] careInfos;
	
	public void setFeatures(String[] features) {
		this.features = features;
	}
	
	public void setCareInfos(String[] careInfos) {
		this.careInfos = careInfos;
	}
	
	public String[] getFeatures() {
		return this.features;
	}
	
	public String[] getCareInfos() {
		return this.careInfos;
	}
}
