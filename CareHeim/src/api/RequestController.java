package api;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import api.model.request.*;
import api.model.response.*;
import clothe.model.*;

public class RequestController {	
	// responseBody? : TCP 한번 연결 이후 정보를 주고받을 때 기존 정보를 저장해둘 객체
	public void executeRequest(Response response, String message) throws IOException {
		Request request = RequestProvider.parsing(message);
		
		if(request != null) {
			System.out.println("device : " + request.getDevice());
			System.out.println("request Type : " + request.getRequestType());
			System.out.println("user : " + request.getUser());
			
			if(request.getDevice() == Request.RASPBERRY) { // 라즈베리 파이
				requestFromRaspberry(response, request.getRequestType(), request.getUser(), (JSONObject) request.getBody());
			} else if(request.getDevice() == Request.MOBILE){ // 안드로이드
				requestFromMobile(response, request.getRequestType(), request.getUser(), (JSONObject) request.getBody());
			} else {
				// 파싱 에러, 각 사이드로 정보 재요청 **동일
			}
		} else {
			// 파싱 실패, 각 사이드로 정보 재요청 **동일
		}
	}
	
	
	// void가 아닌 상태 반환할 것 ex) Exception 등으로 처리
	public void requestFromRaspberry(Response response, int requestType, String user, JSONObject body) throws IOException {
		if(requestType == Request.RP_CLOSE) {
			return ;// 통신 종료
		} else if (requestType > Request.RP_CLOSE || requestType < Request.RP_CLOTHE_INFO) {
			return ; // 에러 처리 - 잘못된 request
		}
		
		if(response != null && response.getUser() != null && !(response.getUser().equals(user))) {
			// 에러 처리 - user 정보가 일치하지 않음
		}
		
		RaspberryRequestBody raspberryRequestBody = RequestProvider.parsingRPBody(body);
		
		if(requestType == Request.RP_CLOTHE_INFO) { // 의류 특징 추출, 정보 반환 요청 - 의류 등록 시	
			ClotheInfoRes[] clotheInfoReses = RequestProvider.getClotheInfos(user, raspberryRequestBody.getImg());
			
			response.setReponsType(Response.RETURN_CLOTHE);
			response.setUser(user);
			response.setResponsBody(clotheInfoReses);
			
			return ; // request 처리 성공
		} else if(requestType == Request.RP_CARE_INFO) { // 의류 특징 추출, 정보 반환 요청 - 세탁 정보 안내 시
			CareInfoRes[] careInfoReses = RequestProvider.getCareInfos(user, raspberryRequestBody.getImg());
			
			response.setReponsType(Response.RETURN_CLOTHE);
			response.setUser(user);
			response.setResponsBody(careInfoReses);
			
			return ; // request 처리 성공 
		} else if (requestType == Request.RP_SAVE) { // DB 저장 요청
			ClotheInfoRes[] beforeResponse = (ClotheInfoRes[]) response.getResponsBody();
			
			DBResultRes dbResultRes
				= RequestProvider.insertClothe(user, raspberryRequestBody.getSavingIndex(), raspberryRequestBody.getExtraFeatures(), beforeResponse);
			
			response.setReponsType(Response.RETURN_DB_STATE);
			response.setUser(user);
			response.setResponsBody(dbResultRes);
			
			return ; // request 처리 성공
		} else {
			// 오류 처리 : 현재 3번 비워져 있음
		}
	}
	
	public void requestFromMobile(Response response, int requestType, String user, JSONObject body) {
		if(requestType == Request.MB_CLOSE) {
			return ;// 통신 종료
		} else if (requestType > Request.MB_CLOSE || requestType < Request.MB_INFO_LATEST) {
			return ; // 에러 처리 - 잘못된 request
		}
		
		MobileRequestBody mobileRequestBody = RequestProvider.parsingMBBody(body);
		
		if(response != null && response.getUser() != null && response.getUser().equals(user)) {
			// 에러 처리 - user 정보가 일치하지 않음
		}
		
		if(requestType == Request.MB_INFO_LATEST) { // 최근 등록 의류 정보 요청
			ClotheInfoResForMobile clotheInfoResForMobile = RequestProvider.getRecentEnroll(user);
			
			response.setReponsType(Response.RETURN_CLOTHE);
			response.setUser(user);
			response.setResponsBody(clotheInfoResForMobile);
			
			return;
		} else if (requestType == Request.MB_INFO) { // 검색한 의류 정보 요청
						// DB thread 호출. User의 Document에서 일치하는 특징의 의류 정보를 꺼내옴 
		} else if (requestType == Request.MB_SAVE){ // 케어라벨 정보 저장 요청
			ClotheInfoResForMobile beforeResponse = (ClotheInfoResForMobile) response.getResponsBody(); 
			
			DBResultRes dbResultRes
				= RequestProvider.updateCareInfo(user, mobileRequestBody.getCareInfos(),  beforeResponse);
			
			response.setReponsType(Response.RETURN_DB_STATE);
			response.setUser(user);
			response.setResponsBody(dbResultRes);
			return;
		} else { // 잘못된 정보 전송됨 : 오류처리
						
		}
	}
}
