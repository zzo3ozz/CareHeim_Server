package model;

public class ColorName {
	public static String getName(double H, double S, double V) {
		String name = "";
		
		return name;
	}
	
	public static String getName(double[] HSV) {
		return getName(HSV[0], HSV[1], HSV[2]);
	}
}
