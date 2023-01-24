package org.mql.java.examples;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import org.mql.java.dom.ProjectDOMParser;
import org.mql.java.models.Project;
import org.mql.java.parsers.ProjectParser;
import org.mql.java.ui.swing.uml.JProject;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public Main(String binPath) {
		configLogger();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			
		}
		
		startParsing(binPath);
	}
	
	private void config() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
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
	
	void startParsing(String binPath) {
		try {
			ProjectParser projectParser = new ProjectParser(binPath);
			Project project = projectParser.getProject();
			System.out.println(projectParser.getProject());
			
			ProjectDOMParser projectDOMParser = new ProjectDOMParser();
			projectDOMParser.persist(project);
			// projectDOMParser.parse(new File("bin/project-dom.xml"));
			// Project project = projectDOMParser.getProject();
			
			if (project != null) {
				JScrollPane panelPane = new JScrollPane(new JProject(project));
				setContentPane(panelPane);
				config();
			}
			
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		if (args.length == 1) {
			new Main(args[0]);
		}
	}
}
