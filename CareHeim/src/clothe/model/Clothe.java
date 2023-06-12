package clothe.model;

import java.util.ArrayList;
import java.util.Arrays;

import clothe.model.color.Color;


// DB : Image파일 경로 저장
// Sever : Base64 인코딩 정보 저장
// 프로그램 : 경로에서 Base64 코드 읽어옴 > 이미지 디코딩 후 사용
// Clothe : 메인 클래스 >> 상속해서 자식 클래스 만들어 사용하기
// type, ptn, color 요소


public class Clothe extends SegmentResult{
	private int ptn;
	private String[] colors;
	
	public Clothe() {
		
	}
	
	public Clothe(SegmentResult seg) {
		super(seg.getType(), seg.getImg());
	}
	
	public Clothe(int type, byte[] img, int ptn, String[] colors) {
		super(type, img);
		this.ptn = ptn;
		this.colors = colors.clone();
	}
	
	public void setPtn(int ptn) {
		this.ptn = ptn;
	}
	
	public void setColors(String[] colors) {
		this.colors = colors.clone();
	}
	
	public void setColors(ArrayList<Color> colors) {
		this.colors = colors.toArray(new String[0]);
	}
	
	public int getPtn() {
		return this.ptn;
	}
	
	public String[] getColors() {
		return this.colors;
	}
}
