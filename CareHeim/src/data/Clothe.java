package data;

//enum TypeName {  }

// enum PtnName

// DB : Image파일 경로 저장
// Sever : Base64 인코딩 정보 저장
// 프로그램 : 경로에서 Base64 코드 읽어옴 > 이미지 디코딩 후 사용

public class Clothe {
	private int id;
	private int type;
	private int ptn;
	private Image img;
	
	private String[] colors;
	private String[] features;
	
	private boolean canDetectStain;
	private boolean hasStain;
	
	private String[] careInfos;
	
	public Clothe(int type, int ptn, String[] colors) {
		this.type = type;
		this.ptn = ptn;
		this.colors = colors.clone();
	}
}
