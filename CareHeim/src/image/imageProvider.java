package image;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Point;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;

public class imageProvider {
	public static Decoder decoder = Base64.getDecoder();
	
	public static Mat decodeImage(String base64) {
		byte[] decodedImage = decoder.decode(base64);
		
		// 이미지 데이터를 MatOfByte 객체로 변환
        MatOfByte matOfByte = new MatOfByte(decodedImage);

        // MatOfByte 객체를 Mat 객체로 변환
        Mat img = Imgcodecs.imdecode(matOfByte, Imgcodecs.IMREAD_COLOR);
		
		return img;
	}
	
	public static Mat[] segmentClothe(Mat img) {
	
		
		// python segment 결과 파싱 
		
		ArrayList clothePolygons = new ArrayList<Point[]>();
		
		int cnt = clothePolygons.size();
		Mat[] clothes = new Mat[cnt];
		
		
		for(int i = 0; i < cnt; i++) {
			Mat mask = new Mat(img.size(), CvType.CV_8UC1, Scalar.all(0));
			Point[] points = (Point[]) clothePolygons.get(i);
			
			MatOfPoint polygon = new MatOfPoint();
			polygon.fromArray(points);
			
			ArrayList list = new ArrayList<MatOfPoint>();
			list.add(polygon);
			
			Imgproc.fillPoly(mask, list, Scalar.all(255));
			
			Mat result = new Mat();
			Core.bitwise_and(img, img, result, mask);
			
			clothes[i] = result;
		}

		return clothes;
	}

}
