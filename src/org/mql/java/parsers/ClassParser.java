package org.mql.java.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.utils.ClazzLoader;

public class ClassParser {
	private String className;
	private Class<?> classe;
	private String modifiers;
	private List<Field> fields;
	private List<Method> methods;
	private Class<?> superClass;
	private List<Constructor<?>> constructors;
	private List<Class<?>> interfaces;
	private List<Class<?>> innerClasses;
	private List<String> inheritanceChain;
	
	public ClassParser(String projectPath, String className) {
		this(ClazzLoader.forName(projectPath, className));
	}
	
	public ClassParser(Class<?> classe) {
		this.classe = classe;
		className = classe.getName();
		modifiers = Modifier.toString(classe.getModifiers());
		fields = new Vector<Field>(Arrays.asList(classe.getDeclaredFields()));
		superClass = classe.getSuperclass();
		methods = new Vector<Method>(Arrays.asList(classe.getDeclaredMethods()));
		constructors = new Vector<Constructor<?>>(Arrays.asList(classe.getDeclaredConstructors()));
		interfaces = new Vector<Class<?>>(Arrays.asList(classe.getInterfaces()));
		innerClasses = new Vector<Class<?>>(Arrays.asList(classe.getDeclaredClasses()));
		loadInheritanceChain();
	}

	private void loadInheritanceChain() {
		inheritanceChain = new Vector<String>();
		Class<?> current = classe;
		
		inheritanceChain.add(className);
		
		while (current.getSuperclass() != null) {
			inheritanceChain.add(current.getSuperclass().getName());
			current = current.getSuperclass();
		}		
	}

	public Class<?> getClasse() {
		return classe;
	}

	public List<Field> getFields() {
		return fields;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public Class<?> getSuperClass() {
		return superClass;
	}

	public List<Constructor<?>> getConstructors() {
		return constructors;
	}

	public List<Class<?>> getInterfaces() {
		return interfaces;
	}

	public List<Class<?>> getInnerClasses() {
		return innerClasses;
	}

	public List<String> getInheritanceChain() {
		return inheritanceChain;
	}
	
	public String getModifiers() {
		return modifiers;
	}
	
	@Override
	public String toString() {
		return "Class : " + className;
	}
}
