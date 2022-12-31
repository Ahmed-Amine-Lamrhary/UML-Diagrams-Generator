package org.mql.java.models;

public class Project {
	private String name;
	private UMLPackage defaultPackage;
	
	public Project(String name, UMLPackage defaultPackage) {
		super();
		this.name = name;
		this.defaultPackage = defaultPackage;
	}

	public UMLPackage getDefaultPackage() {
		return defaultPackage;
	}
	
	public void setDefaultPackage(UMLPackage defaultPackage) {
		this.defaultPackage = defaultPackage;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String out = "Project : " + name + "\n";

		out += "\t" + defaultPackage;
		
		return out;
	}
}
