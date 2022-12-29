package org.mql.java.examples;

import org.mql.java.parsers.ClassParser;
import org.mql.java.parsers.EnumParser;
import org.mql.java.parsers.PackageParser;
import org.mql.java.parsers.ProjectParser;

public class Main {
	String workspacePath = "C:/Users/lamrh/eclipse-workspace";
	String projectName = "p02-generics";

	public Main() {
		
		ProjectParser projectParser = new ProjectParser(workspacePath + "/" + projectName);
		// System.out.println(project);
		
		System.out.println(projectParser.getProject());
				
		/*
		PackageParser packageParser = new PackageParser(projectPath, "org.mql.java.generics");
		System.out.println(packageParser.getPackages());
		System.out.println(packageParser.getClasses());
		System.out.println(packageParser.getInterfaces());
		System.out.println(packageParser.getEnumerations());
		System.out.println(packageParser.getAnnotations());
		*/
		
		/*
		EnumParser enumParser = new EnumParser("org.mql.java.test.EnumTest");
		System.out.println(enumParser.getValues());
		*/
	}

	public static void main(String[] args) {
		new Main();
	}
}
