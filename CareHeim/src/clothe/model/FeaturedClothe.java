package clothe.model;

public class FeaturedClothe extends Clothe{
	private String[] features;
	
	public FeaturedClothe() {
		
	}
	
	public FeaturedClothe(Clothe clothe) {
		super(clothe.getType(), clothe.getImg(), clothe.getPtn(), clothe.getColors());
	}
	
	public FeaturedClothe(int type, byte[] img, int ptn, String[] colors, String[] features) {
		super(type, img, ptn, colors);
		this.features = features;
	}
	
	public void setFeatures(String[] features) {
		this.features = features; 
	}
	
	public String[] getFeatures() {
		return this.features;
	}
}
