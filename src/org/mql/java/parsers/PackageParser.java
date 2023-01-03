package org.mql.java.parsers;

import java.io.File;
import java.util.List;

import java.util.Vector;

import org.mql.java.models.UMLModel;
import org.mql.java.models.UMLPackage;

public class PackageParser {
	private UMLPackage umlPackage;
	
	public PackageParser(String projectPath, String packageName) {
		umlPackage = new UMLPackage("".equals(packageName) ? "default package" : packageName);
		
		String packagePath = packageName.replace(".", "/");
		String fullPath = projectPath + "/bin";
		
		if (!"".equals(packageName))
			fullPath += "/" + packagePath;
		
		File dir = new File(fullPath);
		File f[] = dir.listFiles();
		
		List<UMLModel> models = new Vector<UMLModel>();
		
		if (f != null) {
			for (int i = 0; i < f.length; i++) {
				String name = f[i].getName().replace(".class", "");
				String fullname = "";
				
				if (!"".equals(packageName))
					fullname += packageName + ".";
				
				fullname += name;

				if (f[i].isFile() && f[i].getName().endsWith(".class")) {
					models.add(new ModelParser(projectPath, fullname).getModel());
				}
			}
			
			umlPackage.setModels(models);
		}
	}
	
	public UMLPackage getUmlPackage() {
		return umlPackage;
	}
}
