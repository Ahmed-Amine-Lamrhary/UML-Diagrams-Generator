package org.mql.java.examples;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.mql.java.parsers.ProjectParser;

public class Main {
	private Logger logger = Logger.getLogger(getClass().getName());
	String binPath = "C:/Users/lamrh/eclipse-workspace/p05-Multithreading/bin";

	public Main() {
		configLogger();
				
		try {
			ProjectParser projectParser = new ProjectParser(binPath);
			logger.info(projectParser.getProject().toString());
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
	}
	
	void configLogger() {
		try {
			LogManager.getLogManager().readConfiguration(
				getClass().getResourceAsStream("/logging.properties")
			);
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}
