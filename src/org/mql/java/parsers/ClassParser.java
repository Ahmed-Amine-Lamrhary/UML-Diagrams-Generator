package org.mql.java.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ClassParser {
	private Class<?> classe;
	private String className;
	private String modifiers;
	private List<Field> fields;
	private List<Method> methods;
	private Class<?> superClass;
	private List<Constructor<?>> constructors;
	private List<Class<?>> interfaces;
	private List<Class<?>> innerClasses;
	private String inheritanceChain[];
				
	public ClassParser(String className) throws ClassNotFoundException {
		try {
			classe = Class.forName(className);
			
			this.className = className;
			modifiers = Modifier.toString(classe.getModifiers());
			fields = new Vector<Field>(Arrays.asList(classe.getDeclaredFields()));
			superClass = classe.getSuperclass();
			methods = new Vector<Method>(Arrays.asList(classe.getDeclaredMethods()));
			constructors = new Vector<Constructor<?>>(Arrays.asList(classe.getDeclaredConstructors()));
			interfaces = new Vector<Class<?>>(Arrays.asList(classe.getInterfaces()));
			innerClasses = new Vector<Class<?>>(Arrays.asList(classe.getDeclaredClasses()));
			loadInheritanceChain();			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Erreur : " + e.getMessage());
			throw e;
		}
	}

	private void loadInheritanceChain() {
		if (className == null) {
			inheritanceChain = new String[0];
		}
		else {
			List<String> list = new Vector<String>();
			
			Class<?> current = classe;
			
			list.add(className);
			
			while (current.getSuperclass() != null) {
				list.add(current.getSuperclass().getName());
				current = current.getSuperclass();
			}
			
			inheritanceChain = list.toArray(new String[list.size()]);
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

	public String[] getInheritanceChain() {
		return inheritanceChain;
	}
	
	public String getModifiers() {
		return modifiers;
	}
	
	@Override
	public String toString() {
		return className;
	}
}
