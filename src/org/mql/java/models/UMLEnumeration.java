package org.mql.java.models;

import java.util.List;

public class UMLEnumeration extends UMLModel {
	private List<String> values;

	public UMLEnumeration(String name) {
		super(name);
	}

	public List<String> getValues() {
		return values;
	}
	
	public void setValues(List<String> values) {
		this.values = values;
	}
	
	@Override
	public String toString() {
		return "Enum : " + name;
	}
}
