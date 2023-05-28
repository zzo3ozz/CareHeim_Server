package request;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import clothe.model.Clothe;
import request.model.Request;
import controller.img.*;
import model.ClotheSegResult;
import model.Content;
import model.Image;

public class RequestController {
	public static final int RASPBERRY = 0;
	public static final int MOBILE = 1;
	
	public static final int RS_INFO_ENROLL = 0;
	public static final int RS_SAVE = 1;
	public static final int RS_INFO_CARE = 2;
	public static final int RS_CLOSE = 4;
	
	public static final int MB_INFO_LATEST = 0;
	public static final int MB_INFO = 1;
	public static final int MB_SAVE = 2;
	public static final int MB_CLOSE = 3;
	
	public static JSONParser parser = new JSONParser();
	
	public Object executeRequest(Content info, String message) {
		Request rq = parsing(message);
		
		if(rq != null) {
			if(rq.getDevice() == RASPBERRY) { // 라즈베리 파이
				requestFromRaspberry(info, rq.getRequestType(), (JSONObject) rq.getBody());
			} else if(rq.getDevice() == MOBILE){ // 안드로이드
				requestFromMobile(rq.getRequestType(), (JSONObject) rq.getBody());
			} else {
				// 파싱 에러, 각 사이드로 정보 재요청 **동일
			}
		} else {
			// 파싱 실패, 각 사이드로 정보 재요청 **동일
		}
		
		return null;
	}
	
	public Request parsing(String message) {
		// JSON 파싱
        try {
			JSONObject object = (JSONObject) parser.parse(message);
			
			int deviceType = (int) object.get("device");
			int requestType = (int) object.get("requestType");
			JSONObject body = (JSONObject) object.get("body");
			
			return new Request(deviceType, requestType, body);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.print("파싱 실패");
			return null;
		}
	}
	
	// void가 아닌 상태 반환할 것 ex) Exception 등
	public void requestFromRaspberry(Content existingInfo, int requestType, JSONObject body) {
		if(requestType == RS_CLOSE) {
			return ;// 통신 종료 >> 에러 처리로 구현
		} else if (requestType > RS_CLOSE || requestType < RS_INFO_ENROLL) {
			return ; // 에러 처리 - 잘못된 request
		}
		
		String user = (String) body.get("user");
		
		if(existingInfo.getUser() == null) {
			existingInfo.setUser(user);
		} else {
			if(!existingInfo.getUser().equals(user)) {
				// 에러 처리 - user 정보가 일치하지 않음
			}
		}
		
		if(requestType == RS_INFO_ENROLL) { // 의류 특징 추출, 정보 반환 요청 - 의류 등록 시	
			String img = (String) body.get("img");
			
			Clothe[] clothes = RequestProvider.clotheInfo(img);
			
			existingInfo.setUser(user);
			existingInfo.setClothes(clothes);
			
			return ; // request 처리 성공
		} else if(requestType == RS_INFO_CARE) { // 의류 특징 추출, 정보 반환 요청 - 세탁 정보 안내 시
			String img = (String) body.get("img");
			
			Clothe[] clothes = RequestProvider.careInfo(img);
			
			return ; // request 처리 성공 
		} else if (requestType == RS_SAVE) { // DB 저장 요청
			JSONArray array = (JSONArray) body.get("extraFeatures");
			String[] extraFeatures = (String[]) array.toArray(); // **오류가 안나는지 확인 안해봄
			
			Clothe clothe = existingInfo.getClothes()[0];
			clothe.addFeatures(extraFeatures);
			
			return ; // DB 저장 요청
		} else {
			// 오류 처리 : 현재 3번 비워져 있음
		}
	}
	
	public void requestFromMobile(int requestType, JSONObject body) {
		// 수정 >> 하나의 TCP연결에서 정보 요청-전송 > 세탁정보 등록 진행하도록
		if(requestType == 0) { // 최근 등록 의류 정보 요청
						// DB thread 호출. User의 Document에서 가장 최근 저장된 의류 정보를 꺼내옴
		} else if (requestType == 1) { // 검색한 의류 정보 요청
						// DB thread 호출. User의 Document에서 일치하는 특징의 의류 정보를 꺼내옴 
		} else if (requestType == 2){ // 케어라벨 정보 저장 요청
						// DB thread 호출. 올바른 의류 정보를 찾아 케어 정보를 등록
		} else { // 잘못된 정보 전송됨 : 오류처리
						
		}
	}
}
