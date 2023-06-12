import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class testPy {
	public static void main(String[] args) throws IOException{
		String filePath = "5.jpg";
    	File file = new File(filePath);
    	
    	FileInputStream fis = new FileInputStream(file);
    	
    	byte byteArray[] = new byte[(int)file.length()];
    	fis.read(byteArray);
    	
		String address = "http://220.70.62.115:8001/" + "clothe";
		
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "image/jpeg");
	        conn.setDoOutput(true);
	        
	        OutputStream outputStream = conn.getOutputStream();
            outputStream.write(byteArray);
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
                 
                 System.out.print(response.toString());
            }
            
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
