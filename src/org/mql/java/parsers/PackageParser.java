package org.mql.java.parsers;

import java.io.File;
import java.util.List;
import java.util.Vector;

public class PackageParser {
	private String packageName;
	private List<ClassParser> classes;
	
	public PackageParser(String packageName) {
		classes = new Vector<ClassParser>();
		this.packageName = packageName;
		
		String classPath = System.getProperty("java.class.path");
		String packagePath = packageName.replace(".", "/");
		File dir = new File(classPath + "/" + packagePath);
		File f[] = dir.listFiles();

		for (int i = 0; i < f.length; i++) {
			if (f[i].isFile() && f[i].getName().endsWith(".class")) {
				String name = f[i].getName().replace(".class", "");
				String className = packageName + "." + name;
				
				try {
					classes.add(new ClassParser(className));
				} catch (ClassNotFoundException e) {}
				
			}
		}
	}
	
	public List<ClassParser> getClasses() {
		return classes;
	}
	
	@Override
	public String toString() {
		return packageName;
	}
}
