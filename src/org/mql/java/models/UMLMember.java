package org.mql.java.models;

public abstract class UMLMember {
	protected String name;
	
	public UMLMember(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
