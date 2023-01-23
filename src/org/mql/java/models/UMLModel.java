package org.mql.java.models;

public abstract class UMLModel extends UMLClassifier {
	private String motherModelName;
	
	public UMLModel(String name, String simpleName, String motherModelName) {
		super(name, simpleName);
		this.motherModelName = motherModelName;
	}	
	
	public String getMotherModelName() {
		return motherModelName;
	}
}
