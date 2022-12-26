package org.mql.java.examples;

import org.mql.java.parsers.ClassParser;
import org.mql.java.parsers.EnumParser;
import org.mql.java.parsers.PackageParser;
import org.mql.java.parsers.ProjectParser;

public class Main {
	String projectPath = "C:/Users/lamrh/eclipse-workspace/java-tp";

	public Main() {
		
		ProjectParser projectParser = new ProjectParser(projectPath);
		System.out.println(projectParser);
				
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
