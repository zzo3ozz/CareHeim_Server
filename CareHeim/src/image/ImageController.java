package image;


import java.util.ArrayList;

import clothe.model.Clothe;

public class ImageController {
	public static ArrayList<byte[]> getClotheSegmentation(byte[] img) {
		
		ArrayList<byte[]> clothes = ImageProvider.segmentClothe(img);
		
		return clothes;
	}
}
