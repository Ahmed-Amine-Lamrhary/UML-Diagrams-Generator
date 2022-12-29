package org.mql.java.models;

public abstract class Model {
	protected String name;
	
	public Model(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
