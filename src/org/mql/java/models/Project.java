package org.mql.java.models;

import java.util.List;

public class Project {
	private String name;
	private List<UMLPackage> packages;
		
	public Project(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<UMLPackage> getPackages() {
		return packages;
	}
	
	public void setPackages(List<UMLPackage> packages) {
		this.packages = packages;
	}
	
	@Override
	public String toString() {
		String out = "Project : " + name + "\n";
		out += "-".repeat(out.length()) + "\n";

		for (UMLPackage umlPackage : packages) {
			out += umlPackage;
		}
		
		return out;
	}
}
