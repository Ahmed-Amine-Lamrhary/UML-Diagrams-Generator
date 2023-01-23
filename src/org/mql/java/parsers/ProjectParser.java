package org.mql.java.parsers;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.mql.java.enums.RelationType;
import org.mql.java.models.Project;
import org.mql.java.models.UMLClassifier;
import org.mql.java.models.UMLPackage;
import org.mql.java.models.UMLRelation;

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

	private void parsePackages() {
		logger.info("Loading packages...");
		
		try {
			for (File packageFile : packagesList) {
				UMLPackage p = new PackageParser(packageFile).getUmlPackage();
				project.addPackage(p);
			}
			
			logger.info("Packages loaded");
		} catch(Exception e) {
			logger.warning(e.getMessage());
		}
	}

	private void detectRelations() {
		logger.info("Detecting relations...");
		/*
		for (UMLClassifier classifier : project.getClassifiers()) {
			project.addRelation(new UMLRelation(null, null, null));
		}
		*/
		
		project.addRelation(new UMLRelation(
				project.getClassifiers().get(0),
				project.getClassifiers().get(1),
				RelationType.AGGREGATION
			)
		);
		
		logger.info("Relations detection end");
	}

	@Override
	public void parse(File file) throws Exception {
		if (!file.exists()) throw new Exception("Project not found");
		
		loadPackagesFiles(file);

		project = Project.getInstance();
		project.setName(file.getAbsolutePath());

		try {
			parsePackages();
			detectRelations();
		} catch (Exception e) {
			throw e;
		}
	}
}
