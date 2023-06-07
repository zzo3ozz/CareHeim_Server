package clothe.model;

public class SegmentResult {
	int type;
	byte[] img;
	
	public SegmentResult(int type, byte[] img) {		
		this.type = type;
		this.img = img;
	}
	
	public int getType() {
		return this.type;
	}
	
	public byte[] getImg() {
		return this.img;
	}
}
