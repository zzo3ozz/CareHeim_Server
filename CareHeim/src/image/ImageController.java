package image;

import java.util.ArrayList;

import clothe.model.SegmentResult;

public class ImageController {
	public static ArrayList<SegmentResult> getClotheSegmentation(String filePath, byte[] img) {
		ArrayList<SegmentResult> clothes = ImageProvider.segmentClothe(filePath, img);
		
		return clothes;
	}
}
