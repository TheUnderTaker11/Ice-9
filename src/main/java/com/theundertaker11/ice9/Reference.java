package com.theundertaker11.ice9;

public class Reference {

	public static final String MODID = "ice9";
	public static final String VERSION = "1.0";
	public static final String NAME = "Ice-9";
	public static final String ACCEPTED_MINECRAFT = "[1.12,1.12.2]";
	public static final String CLIENTPROXY = "com.theundertaker11.ice9.proxy.ClientProxy";
	public static final String SERVERPROXY = "com.theundertaker11.ice9.proxy.CommonProxy";

	private Reference() {
		throw new IllegalAccessError("Ref class");
	}
}
