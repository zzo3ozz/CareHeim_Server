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

import clothe.model.SegmentResult;

import java.io.IOException;
import java.util.ArrayList;
 
public class ImageProvider {	
	public static Mat byteToMat(byte[] img) {
		// 이미지 데이터를 MatOfByte 객체로 변환
        MatOfByte matOfByte = new MatOfByte(img);

        // MatOfByte 객체를 Mat 객체로 변환
        Mat mat_img = Imgcodecs.imdecode(matOfByte, Imgcodecs.IMREAD_COLOR);
		
		return mat_img;
	}

	public static byte[] matToByte(Mat mat) {
		MatOfByte matOfByte = new MatOfByte();
        
		Imgcodecs.imencode(".jpg", mat, matOfByte);

        // Get the byte array
        byte[] img = matOfByte.toArray();
		
        return img;
	}
	
	public static ArrayList<SegmentResult> segmentClothe(byte[] img) {
		Mat mat_img = byteToMat(img);
		
		// python segment 결과 --> http request
		
		ArrayList<Point[]> clothePolygons = new ArrayList<Point[]>();
		ArrayList<Integer> clotheType = new ArrayList<Integer>();
		
		int cnt = clothePolygons.size();
		ArrayList clothes = new ArrayList<SegmentResult>();
		
		
		for(int i = 0; i < cnt; i++) {
			Mat mask = new Mat(mat_img.size(), CvType.CV_8UC1, Scalar.all(0));
			Point[] points = clothePolygons.get(i);
			
			MatOfPoint polygon = new MatOfPoint();
			polygon.fromArray(points);
			
			ArrayList list = new ArrayList<MatOfPoint>();
			list.add(polygon);
			
			Imgproc.fillPoly(mask, list, Scalar.all(255));
			
			Mat result = new Mat();
			Core.bitwise_and(mat_img, mat_img, result, mask);
			
			SegmentResult clothe = new SegmentResult(clotheType.get(i), matToByte(result));
			clothes.add(clothe);
		}

		return clothes;
	}
}
