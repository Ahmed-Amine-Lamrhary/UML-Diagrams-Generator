package org.mql.java.models;

import java.util.List;
import java.util.Vector;

import org.mql.java.enums.Visibility;

public class UMLOperation extends UMLProperty {
	private List<String> parameters;
	private boolean _constructor;

	public UMLOperation(String name, Visibility visibility) {
		this(name, visibility, null, null, false, false, true);
	}
	
	public UMLOperation(String name, Visibility visibility, String type, String simpleType, boolean _static, boolean _final, boolean _constructor) {
		super(name, visibility, type, simpleType, _static, _final);
		parameters = new Vector<>();
		this._constructor = _constructor;
	}
	
	public void addParameter(String parameter) {
		parameters.add(parameter);
	}

	public List<String> getParameters() {
		return parameters;
	}
	
	public boolean isConstructor() {
		return _constructor;
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
		
		if (type != null)
			out += " : " + type;
		
		return out;
	}
}
