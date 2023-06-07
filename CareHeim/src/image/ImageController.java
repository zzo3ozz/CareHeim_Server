package image;

import java.util.ArrayList;

import clothe.model.SegmentResult;

public class ImageController {
	public static ArrayList<SegmentResult> getClotheSegmentation(byte[] img) {
		
		ArrayList<SegmentResult> clothes = ImageProvider.segmentClothe(img);
		
		return clothes;
	}
}
