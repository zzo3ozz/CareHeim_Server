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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import AI.HttpRequestProvider;
import clothe.model.SegmentResult;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
 
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
	
	public static ArrayList<SegmentResult> segmentClothe(String file_path, byte[] img) {
		String root_dir = "C:/Users/hhzzo/conda/careheim/seg_result/";
		JSONParser parser = new JSONParser();
		
		ArrayList clothes = new ArrayList<SegmentResult>();
		ArrayList<Point[]> clothePolygons = new ArrayList<Point[]>();
		ArrayList<Integer> clotheType = new ArrayList<Integer>();

        try (FileReader reader = new FileReader(root_dir + file_path + ".txt")) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                int predClass = Long.valueOf(Optional.ofNullable((Long) jsonObject.get("class")).orElse(0L)).intValue();
                JSONArray coordinates = (JSONArray) jsonObject.get("coordinate");
                
                Point[] points = new Point[coordinates.size()];
                
                for(int i = 0; i < coordinates.size(); i++) {
                	JSONArray coord = (JSONArray) coordinates.get(i);
                	points[i] = new Point((long) coord.get(0), (long) coord.get(1) );
                }
                
                clotheType.add(predClass);
                clothePolygons.add(points);
            }
            
            Mat mat_img = byteToMat(img);
    		
    		for(int i = 0; i < clotheType.size(); i++) {
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
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        
        new File(root_dir + file_path + ".txt").delete();
        new File(root_dir + file_path + ".png").delete();
        
        return clothes;
	}
}
