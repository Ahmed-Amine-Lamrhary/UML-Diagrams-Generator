package org.mql.java.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.UMLClass;
import org.mql.java.utils.Utils;

public class ClassParser {
	private String projectPath;
	private Class<?> clazz;
	private UMLClass classe;
	
	public ClassParser(String projectPath, Class<?> clazz, boolean innerClasses) {
		this.projectPath = projectPath;
		this.clazz = clazz;
		
		String name = clazz.getName();
		Class<?> superClass = clazz.getSuperclass();
		String modifiers = Modifier.toString(clazz.getModifiers());

		classe = new UMLClass(name, modifiers, superClass);
		
		List<Constructor<?>> constructors = new Vector<Constructor<?>>(Arrays.asList(clazz.getDeclaredConstructors()));
		List<Class<?>> interfaces = new Vector<Class<?>>(Arrays.asList(clazz.getInterfaces()));
		
		classe.setFields(Utils.getUMLFields(clazz.getDeclaredFields()));
		classe.setMethods(Utils.getUMLMethods(clazz.getDeclaredMethods()));
		
		classe.setConstructors(constructors);
		classe.setInterfaces(interfaces);
		
		loadInheritanceChain();
		
		if (innerClasses) {
			loadInnerClasses();
		}
	}

	private void loadInnerClasses() {
		List<UMLClass> innerClasses = new Vector<UMLClass>();
		
		for (Class<?> c : clazz.getDeclaredClasses()) {
			ClassParser classParser = new ClassParser(projectPath, c, false);
			innerClasses.add(classParser.getClasse());
		}
		
		classe.setInnerClasses(innerClasses);
	}

	private void loadInheritanceChain() {
		List<String> inheritanceChain = new Vector<String>();
		Class<?> current = clazz;
		
		inheritanceChain.add(classe.getName());
		
		while (current.getSuperclass() != null) {
			inheritanceChain.add(current.getSuperclass().getName());
			current = current.getSuperclass();
		}
		
		classe.setInheritanceChain(inheritanceChain);
	}
	
	public UMLClass getClasse() {
		return classe;
	}
}
