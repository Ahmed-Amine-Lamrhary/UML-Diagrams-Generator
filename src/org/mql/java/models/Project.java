package org.mql.java.models;

import java.util.List;

public class Project {
	private List<PackageM> packages;

	public Project() {
	}

	public List<PackageM> getPackages() {
		return packages;
	}
	
	public void setPackages(List<PackageM> packages) {
		this.packages = packages;
	}
	
	@Override
	public String toString() {
		String out = "";
		for (PackageM p : packages) {
			out += p + "\n";
		}
		
		return out;
	}
}
