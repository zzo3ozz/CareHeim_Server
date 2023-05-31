import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.*;
import java.util.Base64;

public class testOpenCV {
    public static void main(String[] args) throws Exception {
    	System.loadLibrary("config/opencv_java470");
    	
    	String filePath = "img_test/t20.png";
    	File file = new File(filePath);
    	
    	FileInputStream fis = new FileInputStream(file);
    	
    	byte byteArray[] = new byte[(int)file.length()];
    	fis.read(byteArray);
    	
    	String base64Image = Base64.getEncoder().encodeToString(byteArray);

         // Base64 문자열을 디코딩하여 이미지 바이트 배열로 변환
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

         // 이미지 데이터를 MatOfByte 객체로 변환
        MatOfByte matOfByte = new MatOfByte(imageBytes);

         // MatOfByte 객체를 Mat 객체로 변환
        Mat matImage = Imgcodecs.imdecode(matOfByte, Imgcodecs.IMREAD_COLOR);

        // Mat 객체의 이미지를 저장
        Imgcodecs.imwrite("decoded_image.jpg", matImage);
        System.out.print("success");
    }

}