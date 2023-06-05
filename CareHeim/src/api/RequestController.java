package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import api.model.request.*;
import api.model.response.*;
import clothe.model.Clothe;
import model.ClotheSegResult;
import model.Image;

public class RequestController {
	public static final int RASPBERRY = 0;
	public static final int MOBILE = 1;
	
	public static final int RP_CLOTHE_INFO = 0;
	public static final int RP_SAVE = 1;
	public static final int RP_CARE_INFO = 2;
	public static final int RP_CLOSE = 4;
	
	public static final int MB_INFO_LATEST = 0;
	public static final int MB_INFO = 1;
	public static final int MB_SAVE = 2;
	public static final int MB_CLOSE = 3;
	
	
	
	// responseBody? : TCP 한번 연결 이후 정보를 주고받을 때 기존 정보를 저장해둘 객체
	public void executeRequest(ResponseBody responseBody, String message) {
		Request request = RequestProvider.parsing(message);
		
		if(request != null) {
			if(request.getDevice() == RASPBERRY) { // 라즈베리 파이
				requestFromRaspberry(responseBody, request.getRequestType(), (JSONObject) request.getBody());
			} else if(request.getDevice() == MOBILE){ // 안드로이드
				requestFromMobile(responseBody, request.getRequestType(), (JSONObject) request.getBody());
			} else {
				// 파싱 에러, 각 사이드로 정보 재요청 **동일
			}
		} else {
			// 파싱 실패, 각 사이드로 정보 재요청 **동일
		}
	}
	
	
	
	// void가 아닌 상태 반환할 것 ex) Exception 등으로 처리
	public void requestFromRaspberry(ResponseBody responseBody, int requestType, JSONObject body) {
		if(requestType == RP_CLOSE) {
			return ;// 통신 종료
		} else if (requestType > RP_CLOSE || requestType < RP_CLOTHE_INFO) {
			return ; // 에러 처리 - 잘못된 request
		}
		
		String user = RequestProvider.parsingRPUser(body);
		
		if(responseBody.getUser() == null) {
			responseBody.setUser(user);
		} else {
			if(!responseBody.getUser().equals(user)) {
				// 에러 처리 - user 정보가 일치하지 않음
			}
		}
		
		
		if(requestType == RP_CLOTHE_INFO) { // 의류 특징 추출, 정보 반환 요청 - 의류 등록 시	
			ExtractFeatures extractFeatures = (ExtractFeatures) RequestProvider.parsingRPBody(requestType, body);
			ClotheInfoArray[] clothes = RequestProvider.clotheInfo(extractFeatures.getImg());
			
			return ; // request 처리 성공
		} else if(requestType == RP_CARE_INFO) { // 의류 특징 추출, 정보 반환 요청 - 세탁 정보 안내 시
			ExtractFeatures extractFeatures = (ExtractFeatures) RequestProvider.parsingRPBody(requestType, body);
			CareInfoArray[] clothes = RequestProvider.careInfo(extractFeatures.getImg());
			
			return ; // request 처리 성공 
		} else if (requestType == RP_SAVE) { // DB 저장 요청
			
			
			return ; // DB 저장 요청
		} else {
			// 오류 처리 : 현재 3번 비워져 있음
		}
	}
	
	public void requestFromMobile(ResponseBody info, int requestType, JSONObject body) {
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
