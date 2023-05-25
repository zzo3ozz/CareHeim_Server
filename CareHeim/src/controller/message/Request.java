package controller.message;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import data.Clothe;
import data.ClotheSegResult;
import data.Image;
import data.RequestMessage;
import controller.img.*;

public class Request {
	public static final int RASPBERRY = 0;
	public static final int RS_INFO_ENROLL = 0;
	public static final int RS_SAVE = 1;
	public static final int RS_INFO_CARE = 2;
	public static final int RS_CLOSE = 4;
	
	public static final int MOBILE = 1;
	public static final int MB_INFO_LATEST = 0;
	public static final int MB_INFO = 1;
	public static final int MB_SAVE = 2;
	public static final int MB_CLOSE = 3;
	
	public static JSONParser parser = new JSONParser();
	
	public Object executeRequest(String message) {
		RequestMessage rq = parsing(message);
		
		if(rq != null) {
			if(rq.getDevice() == RASPBERRY) { // 라즈베리 파이
				requestFromRaspberry(rq.getRequestType(), (JSONObject) rq.getBody());
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
	
	public RequestMessage parsing(String message) {
		// JSON 파싱
        try {
			JSONObject object = (JSONObject) parser.parse(message);
			
			int deviceType = (int) object.get("device");
			int requestType = (int) object.get("requestType");
			JSONObject body = (JSONObject) object.get("body");
			
			return new RequestMessage(deviceType, requestType, body);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.print("파싱 실패");
			return null;
		}
	}
		
	public void requestFromRaspberry(int rt, JSONObject body) {
		if(rt == RS_INFO_ENROLL) { // 의류 특징 추출, 정보 반환 요청 - 의류 등록 시
			Image img = null; //이미지 파일을 불러옴
			
			ClotheSegResult[] segs = controller.img.Segmentation.clothe(img); //의류 세그먼트
			Clothe[] clothes = controller.img.Feature.extractFeature(segs, img);
			
			// clothes 정보 json으로 생성 후 라즈베리파이로 전송
			
			// 응답 대기
			
			// *요청 수신, 데이터 파싱
			
			boolean insertDB = true; // *요청에서 파싱한 정보. DB에 저장할 건지?
			
			if(insertDB) {
				// 데이터 파싱
				boolean isChanged = true;  // 변경된 내용이 있는지?
				
				if(isChanged) {
					// 변경된 내용 반영하여 DB등록
					// DB 서브루틴 호출
					// 서브루틴 종료, 결과 반환
					// 저장 성공 여부 Raspberry로 전송, 전송 성공 여부 확인 후 TCP연결 해제
				}
			} else {
				// 해당 프로세스 종료, TCP 연결해제
			}
			
		} else if(rt == RS_INFO_CARE) { // 의류 특징 추출, 정보 반환 요청 - 세탁 정보 안내 시
			Image img = null; //이미지 파일을 불러옴
			
			ClotheSegResult[] segs = controller.img.Segmentation.clothe(img); //의류 세그먼트
			Clothe[] clothes = controller.img.Feature.extractFeature(segs, img);
			
			// DB 서브루틴 호출
			// DB에서 동일한 의류 검색, careInfo 및 canDetectStain 결과 가져옴
			// true인 경우 오염탐지 실행, 결과 저장
			
			// clothes 정보 json으로 생성 후 라즈베리파이로 전송
			// 전송 성공 여부 확인 후 TCP연결 해제
		} else if (rt == RS_SAVE) { // DB 저장 요청
			
		} else {
			// 잘못된 정보 전송됨 : 오류처리
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
