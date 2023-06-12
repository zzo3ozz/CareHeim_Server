package clothe;

import java.util.ArrayList;
import java.util.Optional;

import org.json.simple.JSONObject;

import AI.HttpRequestProvider;

public class ClotheProvider {	
	public static int getPattern(byte[] img) {
		JSONObject object = HttpRequestProvider.requestPOST("pattern", img);
		
		int ptn = -1;
		
		if(object != null) {
			ptn= Long.valueOf(Optional.ofNullable((Long) object.get("pattern")).orElse(0L)).intValue(); 
		}
				
		return ptn;
	}
	
	public static Boolean canDetectStain(int ptn) {
		if(ptn == 0 || ptn == 4 || ptn == 5 || ptn == 8 || ptn == 10)// 얼룩 탐지가 가능한 패턴
			return false;
		else 
			return true;
	}
	
	public static Boolean getStain(byte[] img) {
		JSONObject object = HttpRequestProvider.requestPOST("stain", img);
		
		boolean hasStain = false;
		
		if(object != null) {
			hasStain= (boolean) object.get("hasStain"); 
		}
		
		return hasStain;
	}
	
//	public static boolean isSame(byte[] img1, byte[] img2) {
//		boolean isSame = true;
//		// openCV를 통해 같은 사진인지 비교 
//		return isSame;
//	}
}
