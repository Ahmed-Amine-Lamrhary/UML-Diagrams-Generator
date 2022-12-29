package org.mql.java.parsers;

import java.util.List;
import java.util.Vector;

import org.mql.java.models.Enumeration;
import org.mql.java.utils.ClasseLoader;

public class EnumParser {
	private Enumeration enumeration;
	
	public EnumParser(String projectPath, String enumName) {
		this(ClasseLoader.forName(projectPath, enumName));
	}
	
	public EnumParser(Class<?> clazz) {
		enumeration = new Enumeration(clazz.getName());
		
		List<String> values = new Vector<String>();
					
		for (Object value : clazz.getEnumConstants()) {
			values.add(value.toString());
		}
		
		enumeration.setValues(values);
	}

	public Enumeration getEnumeration() {
		return enumeration;
	}
}
