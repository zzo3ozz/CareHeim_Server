package AI;

import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HttpRequestProvider {
private static JSONParser parser = new JSONParser();
	
	public static JSONObject requestPOST(String route, byte[] img) {
		String address = "http://localhost:10002/" + "route";
		
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "image/jpeg");
	        conn.setDoOutput(true);
	        
	        OutputStream outputStream = conn.getOutputStream();
            outputStream.write(img);
            outputStream.flush();
            outputStream.close();
            
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            	 BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                 String inputLine;
                 StringBuilder response = new StringBuilder();

                 while ((inputLine = in.readLine()) != null) {
                     response.append(inputLine);
                 }
                 
                 in.close();
                 
                 JSONObject jsonResponse = (JSONObject) parser.parse(response.toString());
                 
                 return jsonResponse;
            }
            
		} catch(ParseException e) {
			return null;
		} catch(IOException e) {
			return null;
		}
		
		return null;
	}
	
	public static String requestSeg(byte[] img) {
		String address = "http://localhost:10002/" + "clothe";
		
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "image/jpeg");
	        conn.setDoOutput(true);
	        
	        OutputStream outputStream = conn.getOutputStream();
            outputStream.write(img);
            outputStream.flush();
            outputStream.close();
            
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            	 BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                 String inputLine;
                 StringBuilder response = new StringBuilder();

                 while ((inputLine = in.readLine()) != null) {
                     response.append(inputLine);
                 }
                 
                 in.close();
                 
                 return response.toString();
            }
            
		} catch(IOException e) {
			return null;
		}
		
		return null;
	}
}
