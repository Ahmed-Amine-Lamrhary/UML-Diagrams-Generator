package org.mql.java.models;

import java.util.List;

import org.mql.java.enums.ModelType;

public class UMLModel {
	private String name;
	private ModelType type;
	private List<UMLAttribute> fields;
	private List<UMLOperation> methods;
	private List<UMLOperation> constructors;
	
	public UMLModel(String name, ModelType type) {
		this.name = name;
		this.type = type;
	}
	
	public List<UMLOperation> getConstructors() {
		return constructors;
	}
	
	public void setConstructors(List<UMLOperation> constructors) {
		this.constructors = constructors;
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

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ModelType getType() {
		return type;
	}
	
	public void setType(ModelType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		String out = "";
		out += type.getName() + " " + name + "\n";
		
		if (constructors != null) {
			for (UMLOperation constructor : constructors) {
				out += "\t \t" + constructor + "\n";
			}			
		}
		
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
