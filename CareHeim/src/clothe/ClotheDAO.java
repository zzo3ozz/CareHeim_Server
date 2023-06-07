package clothe;

import clothe.model.*;

public class ClotheDAO {
	public static int countClothe(String user, int type, int ptn, String[] colors) {
		// DB에서 정보 찾아오기
		
		int number = 0;
		
		return number;
	}
	
	public static int countClothe(String user, int type, int ptn, String[] colors, String[] features) {
		// DB에서 정보 찾아오기
		
		int number = 0;
		
		return number;
	}
	
	public static ClotheDocument selectRecentClothe(String user) {
		ClotheDocument clotheDocument = new ClotheDocument(0, null, 0, new String[] {"빨강"}, new String[] {"특징1"}, new String[] {} ) ;
		
		// DB 연결 후 최근 옷 정보 가져오기
		
		return clotheDocument;
	}
	
	public static ClotheDocument[] selectClothe(String user, int type, int ptn, String[] colors) {
		ClotheDocument[] clotheDocuments = null;
		
		//DB 연결 후 해당 옷 정보 가져오기
		
		return clotheDocuments;
	}
	
	public static ClotheDocument[] selectClothe(String user, int type, int ptn, String[] colors, String[] features) {
		ClotheDocument[] clotheDocuments = null;
		
		// DB 연결 후 특정 옷 정보 가져오기
		
		return clotheDocuments;
	}
	
	public static boolean insertClothe(String user, Clothe clothe, String[] features) {
		
		// DB 연결 후 해당 옷 정보 저장 -> 성공 결과 여부 리턴
		
		boolean isSuccess = true;
		
		return true;
	}
	
	public static boolean updateCareInfos(String user, FeaturedClothe clothe, String[] careInfos) {
		// DB 연결 후 해당 의류의 CareInfo 값 갱신 -> 성공 결과 여부 리턴
		
		boolean isSuccess = true;
		
		return true;
		
	}
}
