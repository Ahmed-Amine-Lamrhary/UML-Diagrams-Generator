package org.mql.java.enums;

public enum RelationType {
	ASSOCIATION("_____"),
	GENERALIZATION("___|>"),
	REALIZATION("---|>"),
	DEPENDENCY("----->"),
	AGGREGATION("---<>"),
	COMPOSITION("---*");
	
	private String symbol;
	
	private RelationType(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return symbol;
	}
}
