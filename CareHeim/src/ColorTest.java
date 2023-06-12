import clothe.model.color.*;


public class ColorTest {
	public static void main(String[] args) {
		Color color = new Color(54, 139, 127);
		
		System.out.println("H : " + color.getH());
		System.out.println("S : " + color.getS());
		System.out.println("V : " + color.getV());
	}
}
