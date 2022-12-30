package org.mql.java.models;

public class UMLAnnotation extends UMLModel {

	public UMLAnnotation(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Annotation : " + name;
	}
}
