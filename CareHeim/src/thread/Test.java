package thread;

import java.io.IOException;

import api.RequestController;
import api.ResponseProvider;
import api.model.response.Response;

public class Test {
	
	public static void main(String[] args) {
		String message = "{\"device\" : 1,\"requestType\" : 0,\"user\" : \"userID\"}";
		
		RequestController rq = new RequestController();
		
		Response rp = new Response<>();
		
		try {
			rq.executeRequest(rp, message);
			String response = ResponseProvider.buildJSON(rp);
			
			System.out.println("-----------------");
			System.out.println(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
