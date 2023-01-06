package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class UMLPackage extends UMLElement {
	private List<UMLClassifier> classifiers;
	
	public UMLPackage(String name) {
		super(name);
		this.classifiers = new Vector<>();
	}
	
	public List<UMLClassifier> getClassifiers() {
		return classifiers;
	}
	
	public void setClassifiers(List<UMLClassifier> classifiers) {
		this.classifiers = classifiers;
	}
	
	public void addClassifier(UMLClassifier classifier) {
		this.classifiers.add(classifier);
	}

	@Override
	public String toString() {
		String out = "";
		
		out += "Package : " + name + "\n";
		
		for (UMLClassifier c : classifiers) {
			out += "\t" + c + "\n";
		}
				
		return out + "\n";
	}

}
