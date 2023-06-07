package clothe.model;

public class ClotheDocument extends FeaturedClothe{
	private String[] careInfos;
	
	public ClotheDocument(Clothe clothe) {
		super(clothe);
	}
	
	public ClotheDocument(FeaturedClothe clothe) {
		super(clothe.getType(), clothe.getImg(), clothe.getPtn(), clothe.getColors(), clothe.getFeatures());
	}
	
	public ClotheDocument(int type, byte[] img, int ptn, String[] colors, String[] features, String[] careInfos) {
		super(type, img, ptn, colors, features);
		this.careInfos = careInfos.clone();
	}
	
	public String[] getCareInfos() {
		return this.careInfos;
	}
}
