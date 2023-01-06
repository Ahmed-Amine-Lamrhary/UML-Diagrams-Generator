package org.mql.java.parsers;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.mql.java.models.Project;
import org.mql.java.models.UMLPackage;

public class ProjectParser implements Parser {
	private Logger logger = Logger.getLogger(getClass().getName());
	private Set<File> packagesList;
	private Project project;
	
	public ProjectParser(String binPath) throws Exception {
		packagesList = new HashSet<>();
		
		logger.info("Parsing project : " + binPath);
		parse(new File(binPath));
		logger.info("Project parsed");
	}
	
	private void loadPackagesFiles(File directory) {                
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                packagesList.add(file.getParentFile());
            } else if (file.isDirectory()) {
            	loadPackagesFiles(file);
            }
        }
    }
	
	public Project getProject() {
		return project;
	}
	
	@Override
	public void parse(File file) throws Exception {				
		loadPackagesFiles(file);
		
		project = Project.getInstance();
		project.setName(file.getAbsolutePath());
		
		try {
			logger.info("Loading packages...");
			
			for (File packageFile : packagesList) {
				UMLPackage p = new PackageParser(packageFile).getUmlPackage();
				project.addPackage(p);
			}
			
			logger.info("Packages loaded");
		} catch(Exception e) {
			throw e;
		}
		
	}
}
