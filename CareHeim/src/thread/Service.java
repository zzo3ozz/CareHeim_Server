package thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.*;

public class Service implements Runnable {
	private Socket client_socket;
	private DataInputStream in;
	private DataOutputStream out;
	
	private Object info; // 하나의 기기에서 in out을 여러번 왕복하며 관리할 정보
	
	public Service(Socket socket) {
		client_socket = socket; // 클라이언트 소켓과 동일
		try {
			this.in = new DataInputStream(client_socket.getInputStream());
			this.out = new DataOutputStream(client_socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			try {
				String message = in.readUTF();					
				System.out.println(message);	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if(client_socket.isClosed()) {
				break;
			} else {
				Thread.yield();
			}
		}
		
		System.out.println("스레드 종료");
	}
}
