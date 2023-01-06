package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public abstract class UMLClassifier extends UMLElement {
	private List<UMLAttribute> fields;
	private List<UMLOperation> methods;
	
	public UMLClassifier(String name) {
		super(name);
		this.fields = new Vector<>();
		this.methods = new Vector<>();
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
	
	public void addUMLAttribute(UMLAttribute attribute) {
		this.fields.add(attribute);
	}
	
	public void addUMLOperation(UMLOperation operation) {
		this.methods.add(operation);
	}
	
	@Override
	public String toString() {
		String out = "";
		out += name + "\n";
				
		if (fields != null) {
			for (UMLAttribute field : fields) {
				out += "\t \t" + field + "\n";
			}
		}
		
		if (methods != null) {
			for (UMLOperation method : methods) {
				out += "\t \t" + method + "\n";
			}
		}
		
		return out;
	}
}
