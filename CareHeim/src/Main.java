import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import thread.*;


// DB 스레드는 따로 관리
// 스레드풀은 연결용으로만!

public class Main {
	final static int PORT = 10001; // 포트번호
	final static int CONNECTS_LIMIT = 5; // 최대 통신 수
	final static long TIME_LIMIT = 5; // 스레드 유휴 대기 시간
		
	public static Socket[] sokets = new Socket[CONNECTS_LIMIT]; // 소켓 목록을 관리할 리스트
	static int index = 0; // sockets의 index를 관리할 변수
	
	// 스레드풀
	static ExecutorService threads = new ThreadPoolExecutor(CONNECTS_LIMIT, CONNECTS_LIMIT, TIME_LIMIT, TimeUnit.MINUTES, new SynchronousQueue<>());
	
	public static void main(String[] args) {
		ServerSocket mainSocket = null;
		
		try {
			mainSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("이미 사용 중인 포트입니다.");
		}
		
		// DB 스레드 생성
		
		while(true) {
			try {
				if(mainSocket != null) {
					System.out.println("Waiting Connection...");
					Socket socket = mainSocket.accept(); // 연결이 생성될 때까지 대기
					
					Service connect = new Service(socket);
					
					try {
						threads.submit(connect); // 작업을 스레드풀에 제출하고 곧바로 return함.
					} catch (NullPointerException e) {
						System.out.print("통신이 비정상적으로 종료되었습니다.");
					} catch (RejectedExecutionException e) {
						System.out.print("스케줄 큐에 등록이 거부되었습니다.");
					}
				} else {
					System.out.println("포트가 열리지 않았습니다.");
					break;
				}
				
				
			} catch (IOException e) {
				System.out.println("IOException Error");
			}
			
		}
		
		System.out.println("프로그램을 종료합니다.");
	}


}
