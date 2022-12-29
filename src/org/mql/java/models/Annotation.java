package org.mql.java.models;

public class Annotation extends Model {

	public Annotation(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Annotation : " + name;
	}
}
