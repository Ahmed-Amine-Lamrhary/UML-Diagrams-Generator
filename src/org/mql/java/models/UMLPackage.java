package org.mql.java.models;

import java.util.List;

public class UMLPackage extends UMLModel {
	private List<UMLPackage> packages;
	private List<UMLClass> classes;
	private List<UMLInterface> interfaces;
	private List<UMLEnumeration> enumerations;
	private List<UMLAnnotation> annotations;
	
	public UMLPackage(String name) {
		super(name);
	}

	public List<UMLPackage> getPackages() {
		return packages;
	}

	public void setPackages(List<UMLPackage> packages) {
		this.packages = packages;
	}

	public List<UMLClass> getClasses() {
		return classes;
	}

	public void setClasses(List<UMLClass> classes) {
		this.classes = classes;
	}

	public List<UMLInterface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<UMLInterface> interfaces) {
		this.interfaces = interfaces;
	}

	public List<UMLEnumeration> getEnumerations() {
		return enumerations;
	}

	public void setEnumerations(List<UMLEnumeration> enumerations) {
		this.enumerations = enumerations;
	}

	public List<UMLAnnotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<UMLAnnotation> annotations) {
		this.annotations = annotations;
	}
	
	@Override
	public String toString() {
		String out = "";
		
		out += "Package : " + name + "\n";
		
		for (UMLClass c : classes) {
			out += "\t" + c + "\n";
		}
		for (UMLAnnotation a : annotations) {
			out += "\t" + a + "\n";
		}
		for (UMLInterface i : interfaces) {
			out += "\t" + i + "\n";
		}
		for (UMLEnumeration e : enumerations) {
			out += "\t" + e + "\n";
		}
		for (UMLPackage p : packages) {
			out += "\t" + p + "\n";
		}
				
		return out + "\n";
	}
}
