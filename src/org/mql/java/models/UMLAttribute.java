package org.mql.java.models;

import org.mql.java.enums.Visibility;

public class UMLAttribute extends UMLProperty {
	
	public UMLAttribute(String name, Visibility visibility, Class<?> type, boolean _static, boolean _final) {
		super(name, visibility, type, _static, _final);
	}

	@Override
	public String toString() {
		String out = "";
		
		if (visibility != null)
			out += visibility.getSymbol() + " ";
		
		out += name;
		
		if (type != null) {
			out += " : ";
			out += type;			
		}
		
		return out;
	}
}
