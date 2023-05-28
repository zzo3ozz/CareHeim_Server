package clothe.model;

public class Color {
	private double H;
	private double S;
	private double V;
	private double pixelFraction;
	private String name;

	
	public Color(int R, int G, int B) {
		double[] HSV = RGBtoHSV(R, G, B);
		setHSV(HSV[0], HSV[1], HSV[2]);
	}
	
	public Color(double H, double S, double V) {
		this.H = H;
		this.S = S;
		this.V = V;
	}
	
	public double[] RGBtoHSV(int R, int G, int B) {
		double[] HSV = new double[3];
		
		return HSV;
	}
	
	public void setHSV(double H, double S, double V) {
		this.H = H;
		this.S = S;
		this.V = V;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPixelFraction(double pixelFraction) {
		this.pixelFraction = pixelFraction;
	}
	
	public double[] getHSV() {
		double[] HSV = {this.H, this.S, this.V};
		
		return HSV;
	}
}
