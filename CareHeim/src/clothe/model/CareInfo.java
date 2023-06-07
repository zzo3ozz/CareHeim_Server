package clothe.model;

public class CareInfo extends ClotheDocument {
	public CareInfo(int type, byte[] img, int ptn, String[] colors, String[] features, String[] careInfos) {
		super(type, img, ptn, colors, features, careInfos);
	}
	
	public CareInfo(Clothe clothe) {
		super(clothe);
	}
	
	public CareInfo(ClotheDocument document) {
		super(document.getType(), document.getImg(), document.getPtn(), document.getColors(), document.getFeatures(), document.getCareInfos());
	}
	
	Boolean canDetectStain;
	Boolean hasStain;
	
	public void setCanDetectStain(boolean flag) {
		this.canDetectStain = flag;
	}
	
	public void setHasStain(boolean flag) {
		this.hasStain = flag;
	}
	
	public boolean getCanDetectStain() {
		return this.canDetectStain;
	}
	
	public boolean getHasStain() {
		return this.hasStain;
	}
}