package clothe.model.color;

public class ColorBloc {
	private double avgH;
	private double avgS;
	private double avgV;
	private double pixelFraction;
	
	public ColorBloc(double H, double S, double V, double pixelFraction) {
		this.avgH = H;
		this.avgS = S;
		this.avgV = V;
		this.pixelFraction = pixelFraction;
	}
	
	public void add(double H, double S, double V, double pixelFraction) {
		this.avgH
			= (this.avgH * this.pixelFraction + H * pixelFraction) / (this.pixelFraction + pixelFraction);
		this.avgS 
			= (this.avgS * this.pixelFraction + S * pixelFraction) / (this.pixelFraction + pixelFraction);;
		this.avgV
			= (this.avgV * this.pixelFraction + V * pixelFraction) / (this.pixelFraction + pixelFraction);
		this.pixelFraction += pixelFraction;
	}
	
	public double getAvgH() {
		return this.avgH;
	}
	
	public double getAvgS() {
		return this.avgS;
	}
	
	public double getAvgV() {
		return this.avgV;
	}
	
	public double getPixelFraction() {
		return this.pixelFraction;
	}
}
