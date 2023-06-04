package api.model.request;

public class AddExtraFeatures extends RequestBody {
	private String[] extraFeatures;
	
	public AddExtraFeatures(String user, String[] extraFeatures) {
		this.user = user;
		this.extraFeatures = extraFeatures;
	}
	
	public void setExtraFeatures(String[] extraFeatures) {
		this.extraFeatures = extraFeatures;
	}
	
	public String[] getExtraFeatures() {
		return this.extraFeatures;
	}
}
