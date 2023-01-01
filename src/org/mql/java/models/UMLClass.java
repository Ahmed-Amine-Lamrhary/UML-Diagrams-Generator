package org.mql.java.models;

import java.util.List;

public class UMLClass extends UMLModel {
	private List<UMLAttribute> fields;
	private List<UMLOperation> methods;
	private Class<?> superClass;
	private List<UMLOperation> constructors;
	private List<Class<?>> interfaces;
	private List<UMLClass> innerClasses;
	private List<String> inheritanceChain;
	
	public UMLClass(String name, String modifiers, Class<?> superClass) {
		super(name);
		
		this.superClass = superClass;
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

	public Class<?> getSuperClass() {
		return superClass;
	}

	public void setSuperClass(Class<?> superClass) {
		this.superClass = superClass;
	}

	public List<UMLOperation> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<UMLOperation> constructors) {
		this.constructors = constructors;
	}

	public List<Class<?>> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<Class<?>> interfaces) {
		this.interfaces = interfaces;
	}

	public List<UMLClass> getInnerClasses() {
		return innerClasses;
	}

	public void setInnerClasses(List<UMLClass> innerClasses) {
		this.innerClasses = innerClasses;
	}

	public List<String> getInheritanceChain() {
		return inheritanceChain;
	}

	public void setInheritanceChain(List<String> inheritanceChain) {
		this.inheritanceChain = inheritanceChain;
	}
	
	@Override
	public String toString() {
		String out = "";
		out += "Class " + name + "\n";
		
		for (UMLOperation constructor : constructors) {
			out += "\t \t" + constructor + "\n";
		}
		
		for (UMLAttribute field : fields) {
			out += "\t \t" + field + "\n";
		}
		
		for (UMLOperation method : methods) {
			out += "\t \t" + method + "\n";
		}
		
		return out;
	}
}
