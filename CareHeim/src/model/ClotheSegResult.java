package model;

public class ClotheSegResult {
	private int type;
	private double[] coordinate;
	
	public ClotheSegResult(int type, double[] coordinate) {
		this.type = type;
		this.coordinate = coordinate;
	}
	
	public int getType() {
		return this.type;
	}
	
	public double[] getCoordinate() {
		return this.coordinate;
	}
}
