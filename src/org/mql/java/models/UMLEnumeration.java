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
		String out = "";
		out += "Enumeration " + name + "\n";
		
		for (String value : values) {
			out += "\t \t" + value + "\n";
		}
		
		return out;
	}
}
