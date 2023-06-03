package request.model;

public class AddExtraFeatures {
	private String user;
	private String[] extraFeatures;
	
	public AddExtraFeatures(String user, String[] extraFeatures) {
		this.user = user;
		this.extraFeatures = extraFeatures;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void setExtraFeatures(String[] extraFeatures) {
		this.extraFeatures = extraFeatures;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String[] getExtraFeatures() {
		return this.extraFeatures;
	}
}
