package clothe.model;

import java.util.ArrayList;
import java.util.Arrays;
//enum TypeName {  }

import model.Image;

// enum PtnName

// DB : Image파일 경로 저장
// Sever : Base64 인코딩 정보 저장
// 프로그램 : 경로에서 Base64 코드 읽어옴 > 이미지 디코딩 후 사용
// Clothe : 메인 클래스 >> 상속해서 자식 클래스 만들어 사용하기
// type, ptn, color 요소


public class Clothe {
	private int id;
	private int type;
	private int ptn;
	
	private String[] colors;
	private ArrayList<String> features;
	
	private boolean canDetectStain;
	private boolean hasStain;
	
	private String[] careInfos;
	
	public Clothe(int type, int ptn, String[] colors) {
		this.type = type;
		this.ptn = ptn;
		this.colors = colors.clone();
	}
	
	public void setFeatures(String[] features) {
		this.features = new ArrayList<String>(Arrays.asList(features));
	}
	
	public void addFeatures(String[] features) {
		for(String feature : features) {
			this.features.add(feature);
		}
	}
	
	public ArrayList<String> getFeatures() {
		return this.features;
	}
}
