package org.mql.java.parsers;

import java.io.File;
import java.util.logging.Logger;

import org.mql.java.models.UMLPackage;
import org.mql.java.utils.StringResolver;

public class PackageParser implements Parser {
	private Logger logger = Logger.getLogger(getClass().getName());
	private UMLPackage umlPackage;
	private File dir;
	
	public PackageParser(File dir) throws Exception {
		this.dir = dir;
		logger.info("Parsing package : " + dir.getAbsolutePath());
		parse(dir);
		logger.info("Package parsed");
	}
	
	private boolean isPackage() {
		if (!dir.isDirectory()) return false;
		for (File file : dir.listFiles()) {
			if (file.isFile()) return true;
		}
		return false;
	}
	
	public UMLPackage getUmlPackage() {
		return umlPackage;
	}
	
	@Override
	public void parse(File file) throws Exception {
		if (!isPackage()) throw new Exception("Package not found");
		
		String packageName = StringResolver.fileName(dir);
		if (packageName == null) packageName = "(default package)";
		
		umlPackage = new UMLPackage(packageName);
		
		File f[] = dir.listFiles();
		
		for (int i = 0; i < f.length; i++) {
			File currentFile = f[i];
			
			if (currentFile.isFile() && currentFile.getName().endsWith(".class")) {
				try {
					ClassifierParser classifierParser = new ClassifierParser(currentFile);
					umlPackage.addClassifier(classifierParser.getClassifier());
				} catch(Exception e) {
					throw e;
				}
			}
		}
	}
}
