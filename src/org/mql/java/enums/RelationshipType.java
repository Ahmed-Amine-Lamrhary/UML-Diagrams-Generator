package org.mql.java.enums;

public enum RelationshipType {
	ASSOCIATION("_____"),
	INHERITANCE("___|>"),
	REALIZATION("---|>"),
	DEPENDENCY("----->"),
	AGGREGATION("---<>"),
	COMPOSITION("----*");
	
	private String symbol;
	
	private RelationshipType(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return symbol;
	}
}
