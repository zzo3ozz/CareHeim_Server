package image;


import clothe.model.Clothe;

public class imageController {
	
	
	public static Clothe[] getClotheInfo(String img) {
		
		segmentClothe();
		extractFeatures();
		
		Clothe[] clothes = null;
		
		
		return clothes;
	}
	
	public static Clothe[] getCareInfo(String img) {
		Clothe[] clothes = getClotheInfo(img);
		
		// DB에서 서치, 케어라벨 정보 가져오고 추가하는 과정
		
		return clothes;
	}
	
	public static void segmentClothe() {
		
	}
	
	public static void findStain() {
		
	}
	
	public static void extractFeatures() {
		
	}
}
