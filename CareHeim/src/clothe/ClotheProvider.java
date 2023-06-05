package clothe;

import java.io.IOException;
import java.util.ArrayList;

import org.opencv.core.Mat;

import clothe.cloudvision.DetectColors;
import clothe.model.Clothe;
import clothe.model.color.Color;
import clothe.model.color.ColorName;
import model.ClotheSegResult;
import model.Image;

public class ClotheProvider {
	public static int getPattern(byte[] img) {
		
		// python 패턴 추출 모델 파싱
		
		int ptn = 0; // 민무늬 : 0
		
		return ptn;
	}
	
	
	
//	public static boolean compare(Image img1, Image img2) {
//		boolean isSame = true;
//		
//		return isSame;
//	}
}
