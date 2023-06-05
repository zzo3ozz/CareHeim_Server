package clothe.model;

public class CareInfo extends Clothe {
	public CareInfo(int type, int ptn, String[] colors) {
		super(type, ptn, colors);
		// TODO Auto-generated constructor stub
	}

	String[] careInfo;
	String[] features;
	Boolean canDetectStain;
	Boolean hasStain;
}