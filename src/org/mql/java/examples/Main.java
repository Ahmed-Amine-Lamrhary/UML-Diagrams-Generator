package org.mql.java.examples;

import org.mql.java.parsers.ProjectParser;

public class Main {
	String binPath = "C:/Users/lamrh/eclipse-workspace/p05-Multithreading/bin";

	public Main() {
		try {			
			ProjectParser projectParser = new ProjectParser(binPath);
			System.out.println(projectParser.getProject());
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Erreur : " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Main();		
	}
}
