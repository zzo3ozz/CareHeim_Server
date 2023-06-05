package clothe.model.color;

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
	
	public Color(double H, double S, double V, double pixelFraction, String name) {
		this.H = H;
		this.S = S;
		this.V = V;
		this.pixelFraction = pixelFraction;
		this.name = name;
	}
	
	public double[] RGBtoHSV(int R, int G, int B) {
		double[] HSV = new double[3];
		
		HSV[0] = 0;
		HSV[1] = 0;
		HSV[2] = 0;
		
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
	
	public double getH() {
		return this.H;
	}
	
	public double getS() {
		return this.S;
	}
	
	public double getV() {
		return this.V;
	}
	
	public double getPixelFraction() {
		return this.pixelFraction;
	}
}
