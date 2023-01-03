package org.mql.java.parsers;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.mql.java.models.Project;
import org.mql.java.models.UMLPackage;

public class ProjectParser {
	private Set<String> packagesList;
	private Project project;
	
	public ProjectParser(String projectPath) {
		packagesList = new HashSet<>();
		
		try {
			loadPackagesList(projectPath + "\\bin");

			project = new Project(projectPath);
			
			List<UMLPackage> packages = new Vector<>();
			
			for (String packageName : packagesList) {
				UMLPackage p = new PackageParser(projectPath, packageName).getUmlPackage();
				packages.add(p);
			}
			
			project.setPackages(packages);
		} catch(NullPointerException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}
	
	private void loadPackagesList(String directoryName) {
        File directory = new File(directoryName);

        File[] fList = directory.listFiles();
        
        for (File file : fList) {
            if (file.isFile()) {
                String path = file.getPath();
                String packName = path.substring(path.indexOf("bin")+4, path.lastIndexOf('\\'));
                packagesList.add(packName.replace('\\', '.'));
            } else if (file.isDirectory()) {
            	loadPackagesList(file.getAbsolutePath());
            }
        }
    }
	
	public Project getProject() {
		return project;
	}
}
