package org.mql.java.models;

public class UMLConstant extends UMLMember {

	public UMLConstant(String name) {
		super(name);
	}
	
	@Override
	public String toString() {
		return name;
	}
}
