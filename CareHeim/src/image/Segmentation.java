package image;

import java.util.ArrayList;

import model.*;

public class Segmentation {
	public static ClotheSegResult[] clothe(Image img) {	
		// ProcessBuilder를 사용, python으로 작성된 clotheSegmentation 모델의 예측 결과를 얻어온다
		
		// 결과 파싱
		int itemN = 0;
		ClotheSegResult[] clothes = new ClotheSegResult[itemN];
		
		// iter
		for(int i = 0; i < itemN; i++) {
			int type = 0; // clothe 종류
			
			int crdN = 0; // 좌표 개수
			double[] coordinate = new double[crdN]; // 각 아이템들의 전체 좌표
			
			for(int j = 0; j < crdN; j++) {
				coordinate[j] = 0; //좌표 파싱
			}
			
			clothes[i] = new ClotheSegResult(type, coordinate);
		}
		
		return clothes;
	}

	public static boolean stain(Image img) {
		boolean hasStain = false;
		
		// ProcessBuilder를 사용, python으로 작성된 stainSegmentation 모델의 예측 결과를 얻어온다
		
		return hasStain;
	}
	
}
