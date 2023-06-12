package api.model.request;

import clothe.model.FeaturedClothe;

public class MobileRequestBody {
	private String[] careInfos;
	private FeaturedClothe featuredClothe;
	
	public void setFeaturedClothe(FeaturedClothe features) {
		this.featuredClothe = features;
	}
	
	public void setCareInfos(String[] careInfos) {
		this.careInfos = careInfos;
	}
	
	public FeaturedClothe getFeaturedClothe() {
		return this.featuredClothe;
	}
	
	public String[] getCareInfos() {
		return this.careInfos;
	}
}
