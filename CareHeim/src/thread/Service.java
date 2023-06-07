package thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.net.Socket;
import java.net.SocketException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Service implements Runnable {
	private Socket client_socket;
	private DataInputStream in;
	private DataOutputStream out;
	
	private Object info; // 하나의 기기에서 in out을 여러번 왕복하며 관리할 정보
	
	public Service(Socket socket) {
		client_socket = socket; // 클라이언트 소켓과 동일
		System.out.println(socket + "is connected running at thread : " + this);
		try {
			this.in = new DataInputStream(client_socket.getInputStream());
			this.out = new DataOutputStream(client_socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		Object requestedBody;
		Object reponseBody;
		
		while(true) {
			try {
				String message = in.readUTF();					
				System.out.println(message);
				
				String outputMessage = "Test message from Server";
				out.writeUTF(outputMessage);
				System.out.println("outputMessage is sended.");
			} catch (SocketException e) {
				System.out.println("Connection 분실");
				break;
			} catch (EOFException e) {
				System.out.println("연결 종료");
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print("입출력 오류");
				break;
			}
		
			
		}
		
		System.out.println(this + " 스레드 종료");
	}
}

