package org.mql.java.models;

public class UMLEnum extends UMLClassifier {
	public UMLEnum(String name, String simpleName) {
		super(name, simpleName);
	}

	@Override
	public String toString() {
		return "Enumeration : " + super.toString();
	}
	
}
