package controller.img;

import java.util.ArrayList;

import data.*;

public class Feature {
	public static Clothe[] extractFeature(ClotheSegResult[] segs, Image img) {
		Clothe[] clothes = new Clothe[segs.length];
		
		for(int i = 0; i < clothes.length; i++) {
			Image img_rmBG = PreProcessing.removeBackgroud(img, segs[i].getCoordinate()); // 배경과 옷 분리
			
			int ptn = getPattern(img_rmBG); // 옷 패턴 추출
			
			// 옷 메인 색상 추출
			Color[] colors = getColors(img_rmBG);
			String[] mainColors = getMainColors(colors);
			
			clothes[i] = new Clothe(segs[i].getType(), ptn, mainColors);
		}
		
		return clothes;
	}
	
	public static int getPattern(Image img) {
		int ptn = 0; // 민무늬 : 0
		
		return ptn;
	}
	
	public static Color[] getColors(Image img) {
		Color[] colors = new Color[10];
		
		// by googleCloudVisionAI, 대표색상 추출
		
		return colors;
	}
	
	public static String[] getMainColors(Color[] color) {
		ArrayList mainColors = new ArrayList<Color>();
		
		// 의류 색상 군집화처리
		
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
