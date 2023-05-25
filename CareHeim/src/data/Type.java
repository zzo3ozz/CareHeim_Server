package data;

public enum Type {
	SHORTSLEEVETOP("반팔 상의"), LONGSLEEVETOP("긴팔 상의"), SHORTSLEEVEOUTWEAR("반팔 자켓"), LONGLSEEVEOUTWEAR("긴팔 자켓"),
	VEST("민소매"), SLING("나시"), SHORTS("반바지"), TROUSERS("긴바지"), SKIRT("치마"),
	SHORTSLEEVEDRESS("반팔 원피스"), LONGSLEEVEDRESS("긴팔 원피스"), VESTDRESS("민소매 원피스"), SLINGDRESS("나시 원피스");
	
	private String name;
	
	private Type(String str) {
		this.name = str;
	}
}
