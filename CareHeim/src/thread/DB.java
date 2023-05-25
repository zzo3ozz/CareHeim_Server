package thread;


public class DB extends Thread{
	public static boolean haveRequest = false;
	public static boolean canAccess = true;
	public static int thread_id = -1;
	public static boolean isSuccess = false;
	public static String request = "";
	public static String respond = "";
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 최초 DB 연결
		while(true) {
			if(haveRequest) {
				// 쿼리 처리
			} else {
				Thread.yield();
			}
		}
	}
	//DB를 연결하는 스레드
	//프로그램 실행 시 최초 연결 후 휴지 > query요청이 있다면 실행 >> 처리 후 휴지
	//한번에 하나의 스레드만 접근 가능하도록 락
}
