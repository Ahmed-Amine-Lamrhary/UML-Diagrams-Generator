package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class Project {
	private String name;
	private static Project project;
	private List<UMLPackage> packages;
	private List<UMLRelation> relations;
	
	public static Project getInstance() {
		if (project != null)
				return project;
		
		project = new Project();
		return project;
	}
	
	private Project() {
		super();
		this.packages = new Vector<>();
		this.relations = new Vector<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<UMLPackage> getPackages() {
		return packages;
	}
	
	public void addPackage(UMLPackage umlPackage) {
		packages.add(umlPackage);
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
	
	@Override
	public String toString() {
		String out = "Project : " + name + "\n";
		out += "-".repeat(out.length()) + "\n";

		for (UMLPackage umlPackage : packages) {
			out += umlPackage + "\n";
		}
				
		for (UMLRelation umlRelation : relations) {
			out += umlRelation + "\n";
		}
		
		return out;
	}
}
