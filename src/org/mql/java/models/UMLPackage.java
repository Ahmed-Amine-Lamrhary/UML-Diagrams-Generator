package org.mql.java.models;

import java.util.List;

public class UMLPackage {
	private String name;
	private List<UMLModel> models;
	
	public UMLPackage(String name) {
		this.name = name;
	}

	public List<UMLModel> getModels() {
		return models;
	}
	
	public void setModels(List<UMLModel> models) {
		this.models = models;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String out = "";
		
		out += "Package : " + name + "\n";
		
		for (UMLModel m : models) {
			out += "\t" + m + "\n";
		}
				
		return out + "\n";
	}
}
