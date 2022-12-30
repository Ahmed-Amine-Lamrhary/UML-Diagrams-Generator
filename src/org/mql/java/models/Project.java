package org.mql.java.models;

import java.util.List;

public class Project {
	private List<UMLPackage> packages;

	public Project() {
	}

	public List<UMLPackage> getPackages() {
		return packages;
	}
	
	public void setPackages(List<UMLPackage> packages) {
		this.packages = packages;
	}
	
	@Override
	public String toString() {
		String out = "";
		for (UMLPackage p : packages) {
			out += p + "\n";
		}
		
		return out;
	}
}
