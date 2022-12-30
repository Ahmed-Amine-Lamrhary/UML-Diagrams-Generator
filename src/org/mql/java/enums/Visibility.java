package org.mql.java.enums;

public enum Visibility {
	PUBLIC("+"),
	PRIVATE("-"),
	PROTECTED("#"),
	PACKAGE("~");
	
	private String symbol;
	
	private Visibility(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return symbol;
	}
}
