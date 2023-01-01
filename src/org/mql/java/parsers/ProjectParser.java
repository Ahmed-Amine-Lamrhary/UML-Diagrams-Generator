package org.mql.java.parsers;

import org.mql.java.models.UMLPackage;

import java.io.File;

import org.mql.java.models.Project;

public class ProjectParser {
	private Project project;
	
	public ProjectParser(String projectPath) {		
		try {
			File dir = new File(projectPath);
			UMLPackage defaultPackage = new PackageParser(projectPath).getUmlPackage();
			project = new Project(dir.getName(), defaultPackage);
		} catch(NullPointerException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}
	
	public Project getProject() {
		return project;
	}
}
