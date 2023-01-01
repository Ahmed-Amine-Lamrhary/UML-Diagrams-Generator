package org.mql.java.models;

import java.util.List;

public class UMLAnnotation extends UMLModel {
	private List<UMLOperation> methods;
	
	public UMLAnnotation(String name) {
		super(name);
	}

	public List<UMLOperation> getMethods() {
		return methods;
	}
	
	public void setMethods(List<UMLOperation> methods) {
		this.methods = methods;
	}
	
	@Override
	public String toString() {
		String out = "";
		out += "Annotation " + name + "\n";
		
		for (UMLOperation method : methods) {
			out += "\t \t \t" + method + "\n";
		}
		
		return out;
	}
}
