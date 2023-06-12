package clothe.model;

public class SegmentResult {
	int type;
	byte[] img;
	
	public SegmentResult() {
		
	}
	
	public SegmentResult(int type, byte[] img) {		
		this.type = type;
		this.img = img;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
	
	public byte[] getImg() {
		return this.img;
	}
}
