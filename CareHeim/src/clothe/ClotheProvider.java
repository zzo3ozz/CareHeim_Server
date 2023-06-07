package clothe;

import java.io.IOException;
import java.util.ArrayList;

import clothe.model.*;
import clothe.model.color.*;

public class ClotheProvider {
	public static int getPattern(byte[] img) {
		
		// python 패턴 추출 모델 파싱 --> httpRequest
		
		int ptn = 0; // 민무늬 : 0
		
		return ptn;
	}
	
	public static Boolean canDetectStain(int ptn) {
		if(ptn == 1)// 얼룩 탐지가 가능한 패턴
			return true;
		else 
			return false;
	}
	
	public static Boolean getStain(byte[] img) {
		boolean hasStain = false;
		
		// python 패턴 추출 모델 파싱 --> httpRequest
		
		return hasStain;
	}
	
	public static boolean isSame(byte[] img1, byte[] img2) {
		boolean isSame = true;
		// openCV를 통해 같은 사진인지 비교 
		return isSame;
	}
}
