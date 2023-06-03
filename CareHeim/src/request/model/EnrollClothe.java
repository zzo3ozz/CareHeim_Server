package request.model;

public class EnrollClothe {
	private String user;
	private String img;
	
	public EnrollClothe(String user, String img) {
		this.user = user;
		this.img = img;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String getImg() {
		return this.img;
	}
}
