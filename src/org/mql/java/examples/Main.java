package org.mql.java.examples;

import org.mql.java.parsers.ProjectParser;

public class Main {
	String workspacePath = "C:/Users/lamrh/eclipse-workspace";
	String projectName = "Lamrhary Ahmed Amine - TP-03-2 UML Diagrams Generator";

	public Main() {		
		ProjectParser projectParser = new ProjectParser(workspacePath + "/" + projectName);
		
		System.out.println(projectParser.getProject());
	}

	public static void main(String[] args) {
		new Main();		
	}
}
