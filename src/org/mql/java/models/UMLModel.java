package org.mql.java.models;

public abstract class UMLModel {
	protected String name;
	
	public UMLModel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
