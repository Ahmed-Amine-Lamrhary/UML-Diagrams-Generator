package org.mql.java.models;

public class UMLInterface extends UMLModel {

	public UMLInterface(String name, String simpleName, String motherModelName) {
		super(name, simpleName, motherModelName);
	}

	@Override
	public String toString() {
		return "Interface " + super.toString();
	}
	
}
