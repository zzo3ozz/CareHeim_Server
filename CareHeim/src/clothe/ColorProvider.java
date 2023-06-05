package clothe;

import java.io.IOException;
import java.util.ArrayList;

import clothe.cloudvision.DetectColors;
import clothe.model.color.*;

public class ColorProvider {
	public static Color[] getColors(byte[] img) throws IOException {
		ArrayList<Color> colors_tmp = DetectColors.detectColors(img);
		
		Color[] colors = colors_tmp.toArray(new Color[0]);
		
		// 의류 색상 군집화처리
		
		return colors;
	}
	
	public static ArrayList<ColorBloc> ClustringColor(Color[] colors){
		ArrayList<ColorBloc> blocs = new ArrayList<ColorBloc>();
		
		for(int i = 0; i < colors.length; i++) {
			Color color = colors[i];
			
			if(blocs.isEmpty()) {
				blocs.add(new ColorBloc(color.getH(), color.getS(), color.getV(), color.getPixelFraction()));
			} else {
				for(ColorBloc bloc : blocs) {
					if(Math.abs(color.getH() - bloc.getAvgH()) <= 15 && Math.abs(color.getS() - bloc.getAvgS()) <= 30 && Math.abs(color.getV() - bloc.getAvgV()) <= 30) {
						bloc.add(color.getH(), color.getS(), color.getV(), color.getPixelFraction());
					} else {
						blocs.add(new ColorBloc(color.getH(), color.getS(), color.getV(), color.getPixelFraction()));
					}
				}
			}
		}
		
		return blocs;
	}
	
	public static ArrayList<Color> getMainColors(byte[] img) throws IOException {
		Color[] colors = getColors(img);
		
		ArrayList<ColorBloc> blocs = ClustringColor(colors);
				
		ArrayList<Color> mainColors = new ArrayList<Color>();
				
		for(int i = 0; i < blocs.size(); i++) {
			ColorBloc bloc = blocs.get(i);
			String name = ColorName.getName(bloc.getAvgH(), bloc.getAvgS(), bloc.getAvgV());
			
			Color newColor = new Color(bloc.getAvgH(), bloc.getAvgS(), bloc.getAvgV(), bloc.getPixelFraction(), name);
			mainColors.add(newColor);
		}
		
		return mainColors;
	}
}
