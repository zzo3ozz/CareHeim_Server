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
	private int type;
	private int ptn;
	private String[] colors;

	public Clothe(int type, int ptn, String[] colors) {
		this.type = type;
		this.ptn = ptn;
		this.colors = colors.clone();
	}
}
