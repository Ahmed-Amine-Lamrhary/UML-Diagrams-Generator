package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class UMLPackage {
	private String name;
	private List<UMLClassifier> classifiers;
	
	public UMLPackage(String name) {
		this.name = name;
		this.classifiers = new Vector<>();
	}
	
	public String getName() {
		return name;
	}
	
	public List<UMLClassifier> getClassifiers() {
		return classifiers;
	}
	
	public void addClassifier(UMLClassifier classifier) {
		this.classifiers.add(classifier);
	}

	@Override
	public String toString() {
		String out = "Package : " + name + "\n";
		
		for (UMLClassifier c : classifiers) {
			out += "\t" + c + "\n";
		}
				
		return out + "\n";
	}

}
