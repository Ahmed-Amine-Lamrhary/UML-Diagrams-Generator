package org.mql.java.parsers;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.UMLPackage;
import org.mql.java.models.Project;

public class ProjectParser {
	private String projectPath;
	private Project project;
	
	public ProjectParser(String projectPath) {
		this.projectPath = projectPath;
		project = new Project();
		
		try {
			loadPackages();
			
			new RelationshipParser(project);
		} catch(NullPointerException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}
	
	private void loadPackages() {
		File src = new File(projectPath + "/bin");
		
		List<UMLPackage> packages = new Vector<>();
		
		for (File file : src.listFiles()) {
			if (file.isDirectory()) {
				packages.add(new PackageParser(projectPath, file.getName()).getPackageM());
			}
		}
		
		project.setPackages(packages);
	}
	
	public Project getProject() {
		return project;
	}
	
	public String getProjectPath() {
		return projectPath;
	}
}
