package data;

public class RequestMessage {
	private int device;
	private int requestType;
	private Object body;
	
	public RequestMessage(int device, int requestType, Object body) {
		this.device = device;
		this.requestType = requestType;
		this.body = body;
	}
	
	public int getDevice() {
		return this.device;
	}
	
	public int getRequestType() {
		return this.requestType;
	}
	
	public Object getBody() {
		return this.body;
	}
}
