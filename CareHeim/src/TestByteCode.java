import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestByteCode {
	public static void main(String[] args) throws IOException {
		String filePath = "img_test/t20.png";
    	File file = new File(filePath);
    	
    	FileInputStream fis = new FileInputStream(file);
    	
    	byte byteArray[] = new byte[(int)file.length()];
    	fis.read(byteArray);
    	
    	System.out.println(byteArray.toString());
	}
}
