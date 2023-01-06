package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class Project {
	private String name;
	private static Project project;
	private List<UMLPackage> packages;
	
	public static Project getInstance() {
		if (project != null)
				return project;
		
		project = new Project();
		return project;
	}
	
	private Project() {
		super();
		this.packages = new Vector<>();
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
	
	public void addPackage(UMLPackage pUmlPackage) {
		this.packages.add(pUmlPackage);
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
