package request.model;

import clothe.model.Clothe;

public class Content {
	private String user;
	private Clothe[] clothes;
	private String[] extraFeatures;
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setClothes(Clothe clothe) {
		this.clothes = new Clothe[1];
		this.clothes[0] = clothe;
	}
	
	public void setClothes(Clothe[] clothes) {
		this.clothes = clothes;
	}
	
	public void setExtraFeatures(String[] extraFeatures) {
		this.extraFeatures = extraFeatures;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public Clothe[] getClothes() {
		return this.clothes;
	}
	
	public String[] getExtraFeatures() {
		return this.extraFeatures;
	}
}
 