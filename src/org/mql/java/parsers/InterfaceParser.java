package org.mql.java.parsers;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.UMLInterface;
import org.mql.java.utils.ClasseLoader;

public class InterfaceParser {
	private UMLInterface interfacee;
	
	public InterfaceParser(String projectPath, String interfaceName) {
		this(ClasseLoader.forName(projectPath, interfaceName));
	}
	
	public InterfaceParser(Class<?> clazz) {
		interfacee = new UMLInterface(clazz.getName());
		
		List<Field> fields = new Vector<Field>(Arrays.asList(clazz.getDeclaredFields()));
		List<Method> methods = new Vector<Method>(Arrays.asList(clazz.getDeclaredMethods()));
	
		interfacee.setFields(fields);
		interfacee.setMethods(methods);
	}
	
	public UMLInterface getInterface() {
		return interfacee;
	}
}
