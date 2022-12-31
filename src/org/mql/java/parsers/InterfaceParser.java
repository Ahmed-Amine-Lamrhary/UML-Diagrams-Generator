package org.mql.java.parsers;

import org.mql.java.models.UMLInterface;
import org.mql.java.utils.ClasseLoader;
import org.mql.java.utils.Utils;

public class InterfaceParser {
	private UMLInterface interfacee;
	
	public InterfaceParser(String projectPath, String interfaceName) {
		this(ClasseLoader.forName(projectPath, interfaceName));
	}
	
	public InterfaceParser(Class<?> clazz) {
		interfacee = new UMLInterface(clazz.getName());
		interfacee.setFields(Utils.getUMLFields(clazz.getDeclaredFields()));
		interfacee.setMethods(Utils.getUMLMethods(clazz.getDeclaredMethods()));
	}
	
	public UMLInterface getInterface() {
		return interfacee;
	}
}
