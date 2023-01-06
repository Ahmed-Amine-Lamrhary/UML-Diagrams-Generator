package org.mql.java.models;

import java.util.List;

import org.mql.java.enums.Visibility;

public class UMLOperation {
	private Visibility visibility;
	private String name;
	private String returnType;
	private boolean isStatic;
	private List<UMLAttribute> parameters;

	public UMLOperation(Visibility visibility, String name) {
		super();
		this.visibility = visibility;
		this.name = name;
	}
	
	public UMLOperation(Visibility visibility, String name, String returnType, boolean isStatic) {
		super();
		this.visibility = visibility;
		this.name = name;
		this.returnType = returnType;
		this.isStatic = isStatic;
	}
	
	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public List<UMLAttribute> getParameters() {
		return parameters;
	}

	public void setParameters(List<UMLAttribute> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		String out = "";
		out += visibility.getSymbol() + " " + name + "(";
		
		for (int i = 0; i < parameters.size(); i++) {
			out += parameters.get(i);
			
			if (i < parameters.size()-1)
				out += ", ";
		}
		
		out += ")";
		
		if (returnType != null)
			out += " : " + returnType;
		
		return out;
	}
}
