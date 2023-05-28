package clothe;

import java.util.ArrayList;

import org.opencv.core.Mat;

import clothe.model.Clothe;
import clothe.model.Color;
import clothe.model.ColorName;
import model.ClotheSegResult;
import model.Image;

public class clotheProvider {
	public static int getPattern(Mat img) {
		
		// python 패턴 추출 모델 파싱
		
		int ptn = 0; // 민무늬 : 0
		
		return ptn;
	}
	
	public static Color[] getColors(Image img) {
		Color[] colors = new Color[10];
		
		// by googleCloudVisionAI, 대표색상 추출
		
		// 의류 색상 군집화처리
		
		return colors;
	}
	
	public static String[] getMainColors(Color[] color) {
		ArrayList mainColors = new ArrayList<Color>();
		
		
		
		String[] names = new String[mainColors.size()];
		
		for(int i = 0; i < names.length; i++) {
			names[i] = ColorName.getName(((Color) mainColors.get(i)).getHSV());
		}
		
		return names;
	}
	
	public static boolean compare(Image img1, Image img2) {
		boolean isSame = true;
		
		return isSame;
	}
}
