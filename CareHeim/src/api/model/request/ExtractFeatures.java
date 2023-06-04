package api.model.request;

public class ExtractFeatures extends RequestBody{
	byte[] img;
	
	public ExtractFeatures(String user, byte[] img) {
		this.user = user;
		this.img = img;
	}
	
	public void setImg(byte[] img) {
		this.img = img;
	}
	
	public byte[] getImg() {
		return this.img;
	}
}
