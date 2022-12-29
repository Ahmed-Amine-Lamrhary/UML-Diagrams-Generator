package org.mql.java.models;

import java.util.List;

public class PackageM extends Model {
	private List<PackageM> packages;
	private List<Classe> classes;
	private List<Interface> interfaces;
	private List<Enumeration> enumerations;
	private List<Annotation> annotations;
	
	public PackageM(String name) {
		super(name);
	}

	public List<PackageM> getPackages() {
		return packages;
	}

	public void setPackages(List<PackageM> packages) {
		this.packages = packages;
	}

	public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	public List<Interface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	public List<Enumeration> getEnumerations() {
		return enumerations;
	}

	public void setEnumerations(List<Enumeration> enumerations) {
		this.enumerations = enumerations;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}
	
	@Override
	public String toString() {
		String out = "";
		
		out += "Package : " + name + "\n";
		
		for (PackageM p : packages) {
			out += "\t" + p + "\n";
		}
		for (Classe c : classes) {
			out += "\t" + c + "\n";
		}
		for (Annotation a : annotations) {
			out += "\t" + a + "\n";
		}
		for (Interface i : interfaces) {
			out += "\t" + i + "\n";
		}
		for (Enumeration e : enumerations) {
			out += "\t" + e + "\n";
		}
		
		return out;
	}
}
