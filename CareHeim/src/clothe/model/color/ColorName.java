package clothe.model.color;

public class ColorName {
	public static String getName(double H, double S, double V) {
		String name = "";
		
		if(V < 10) {
			name = "검정색";
		} else if(S < 0.05 && V > 0.95) {
			name = "흰색";
		} else if(S < 0.05) {
			if(V > 0.7 && V < 0.95) {
				name = "밝은 ";
			} else if(V < 40) {
				name = "어두운 ";
			}
			name += "회색";
		} else {
			if(H < 15 || H > 345) { //red
				name = "빨강색";
				if(S < 70) 
					name = "분홍색";
			} else if (H < 45) { // orange
				if(S < 75) 
					name += "연한 ";
				name += "주황색";
			} else if (H < 70) { // yellow
				if(S < 75) 
					name += "연한 ";
				name += "노란색";
			} else if (H < 160) { // green
				if(S < 60) 
					name += "연한 ";
				name += "초록색";
			} else if (H < 205) { // sky blue
				if(S < 60) 
					name += "연한 ";
				name += "하늘색";
			} else if (H < 240) { // blue
				if(S < 70) {
					if(H > 230) 
						name+= "연한 보라색";
					else
						name = "하늘색";
				} else {
					name += "파란색";
				}
			} else if (H < 285) { // purple
				if(S < 65) 
					name += "연한 ";
				name += "보라색";
			} else { // rose
				if(S < 70)
					name += "연한 ";
				name += "자주색";
			}
		}
		
		return name;
	}
}
