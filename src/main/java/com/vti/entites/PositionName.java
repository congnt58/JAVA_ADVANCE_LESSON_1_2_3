package com.vti.entites;

public enum PositionName {
	DEV1("DEV1"), DEV2("DEV2");
	
	private String value;

	private PositionName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
	
}
