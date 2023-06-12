package clothe.model.color;

public class Color {
	private double H;
	private double S;
	private double V;
	private double pixelFraction;
	private String name;

	
	public Color(int R, int G, int B) {
		double[] HSV = RGBtoHSV(R, G, B);
		this.H = HSV[0];
		this.S = HSV[1];
		this.V = HSV[2];
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
		
		double var_R = (double) R / 255, var_G = (double) G / 255, var_B = (double) B / 255;
		
		double max = Math.max(Math.max(var_R, var_G), var_B);		
		double min = Math.min(Math.min(var_R, var_G), var_B);
		
		double delta = max - min;
		
		HSV[2] = max;
		
		if(delta == 0) {
			HSV[0] = 0;
			HSV[1] = 0;
		} else {
			HSV[1] = delta / max;
			
			if(var_R == max) {
				HSV[0] = ((var_G - var_B) / delta) % 6;
			} else if(var_G == max) {
				HSV[0] = (var_B - var_R) / delta + 2;
			} else {
				HSV[0] = (var_R - var_G) / delta + 4; 
			}
			
			HSV[0] *= 60;
			
			if(HSV[0] < 0)
				HSV[0] += 360;
		}
		
		HSV[1] *= 100;
		HSV[2] *= 100;
		
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
