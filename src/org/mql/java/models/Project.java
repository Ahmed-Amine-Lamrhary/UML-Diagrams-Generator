package org.mql.java.models;

import java.util.List;
import java.util.Vector;

import org.mql.java.utils.Utils;

public class Project {
	private String name;
	private static Project project;
	private List<UMLPackage> packages;
	private List<UMLRelation> relations;
	
	public static Project getInstance(String name) {
		if (project != null)
				return project;
		
		project = new Project(name);
		return project;
	}
	
	private Project(String name) {
		this.name = name;
		this.packages = new Vector<>();
		this.relations = new Vector<>();
	}

	public String getName() {
		return name;
	}
	
	public List<UMLPackage> getPackages() {
		return packages;
	}
	
	public void addPackage(UMLPackage umlPackage) {
		packages.add(umlPackage);
	}
	
	public void addRelation(UMLRelation relation) {
		this.relations.add(relation);
	}
	
	public void addRelations(List<UMLRelation> relations) {
		this.relations.addAll(relations);
	}
	
	public List<UMLRelation> getRelations() {
		return relations;
	}
	
	public List<UMLModel> getModels() {
		List<UMLModel> models = new Vector<>();
		
		for (UMLPackage umlPackage : packages) {
			for (UMLClassifier umlClassifier : umlPackage.getClassifiers()) {
				if (umlClassifier instanceof UMLModel) {
					models.add((UMLModel) umlClassifier);
				}
			}
		}
		
		return models;
	}
	
	public UMLModel getModel(String name) {
		for (UMLPackage umlPackage : packages) {
			for (UMLClassifier umlClassifier : umlPackage.getClassifiers()) {
				if (umlClassifier instanceof UMLModel && umlClassifier.getName().equals(name)) {
					return (UMLModel) umlClassifier;
				}
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		String out = Utils.getPrintableTitle("Project : " + name);

		if (packages.size() > 0) {
			out += Utils.getPrintableSubtitle("List of packages");
		} else {
			out += Utils.getPrintableSubtitle("No packages were found");
		}
		
		for (UMLPackage umlPackage : packages) {
			out += umlPackage + "\n";
		}
		
		if (relations.size() > 0) {
			out += Utils.getPrintableSubtitle("List of relations");			
		} else {
			out += Utils.getPrintableSubtitle("No relations were found");
		}
		
		for (UMLRelation umlRelation : relations) {
			out += umlRelation + "\n";
		}
		
		return out;
	}
}
