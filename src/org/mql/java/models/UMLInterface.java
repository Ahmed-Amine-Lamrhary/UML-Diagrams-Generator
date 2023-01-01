package org.mql.java.models;

import java.util.List;

public class UMLInterface extends UMLModel {
	private List<UMLAttribute> fields;
	private List<UMLOperation> methods;
	
	public UMLInterface(String name) {
		super(name);
	}
	
	public List<UMLAttribute> getFields() {
		return fields;
	}

	public void setFields(List<UMLAttribute> fields) {
		this.fields = fields;
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
		out += "Interface " + name + "\n";
		
		for (UMLAttribute field : fields) {
			out += "\t \t" + field + "\n";
		}
		
		for (UMLOperation method : methods) {
			out += "\t \t" + method + "\n";
		}
		
		return out;
	}
}
