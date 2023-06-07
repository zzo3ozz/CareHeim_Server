package api.model.request;

public class RaspberryRequestBody {
	private byte[] img;
	private String[] extraFeatures;
	private int savingIndex;
	
	
	public void setImg(byte[] img) {
		this.img = img;
	}
	
	public void setExtraFeatures(String[] extraFeatures) {
		this.extraFeatures = extraFeatures;
	}
	
	public void setSavingIndex(int index) {
		this.savingIndex = index;
	}
	
	public String[] getExtraFeatures() {
		return this.extraFeatures;
	}
	
	public byte[] getImg() {
		return this.img;
	}
	
	public int getSavingIndex() {
		return this.savingIndex;
	}
}
