package clothe.cloudvision;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.ColorInfo;
import com.google.cloud.vision.v1.DominantColorsAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

import clothe.model.color.Color;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetectColors {
    public static ArrayList detectColors(byte[] byte_img) throws IOException {
    	List<AnnotateImageRequest> requests = new ArrayList<>();
    	ByteString imgBytes = ByteString.copyFrom(byte_img);
        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.IMAGE_PROPERTIES).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests. After completing all of your requests, call
    // the "close" method on the client to safely clean up any remaining background resources.
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
        	BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
        
            List<AnnotateImageResponse> responses = response.getResponsesList();
            
            ArrayList colors = new ArrayList<Color>();
            for (AnnotateImageResponse res : responses) {
            	if (res.hasError()) {
            		System.out.format("Error: %s%n", res.getError().getMessage());
            		return null;
            	}
            
            // For full list of available annotations, see http://g.co/cloud/vision/docs
            	DominantColorsAnnotation colorsInfo = res.getImagePropertiesAnnotation().getDominantColors();
            	
            	
            	for (ColorInfo color : colorsInfo.getColorsList()) {
            		Color temp = new Color((int) color.getColor().getRed(), (int) color.getColor().getGreen(), (int) color.getColor().getBlue());
            		temp.setPixelFraction(color.getPixelFraction());
            		colors.add(temp);
            	}
            }
            return colors;
        }
    }
}