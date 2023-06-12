package clothe;

import java.io.IOException;
import java.util.ArrayList;

import clothe.model.*;

public class ClotheController {
	public static final int	NONEXIST_DB = -1;
	public static final int SUCCESS = 0;
	// public static final int OVER_TWO = 1;
	
	
	public static Clothe extractFeature(SegmentResult segmentResult) {
		Clothe clothe = new Clothe(segmentResult);
		
		clothe.setPtn(ClotheProvider.getPattern(segmentResult.getImg()));
		clothe.setColors(ColorProvider.getMainColors(segmentResult.getImg()));
		System.out.println("extractFeatures success");
		return clothe;
	}
	
	public static int countClothe(String user, FeaturedClothe clothe) {
		return ClotheDAO.countClothe(user, clothe.getType(), clothe.getPtn(), clothe.getColors(), clothe.getFeatures());
	}
	
	public static int searchForOneClothe(String user, Clothe clothe, ClotheDocument result) {		
//		ClotheDocument[] list = ClotheDAO.selectClothe(user, clothe.getType(), clothe.getPtn(), clothe.getColors());
//		
//		if(list == null) {
//			return NONEXIST_DB;
//		}
		
//		for(int i = 0; i < list.length; i++) {
//			if(ClotheProvider.isSame(list[i].getImg(), clothe.getImg())) {
//				result = list[i];
//				return SUCCESS;
//			}
//		}
		
		return NONEXIST_DB;
	}
}
