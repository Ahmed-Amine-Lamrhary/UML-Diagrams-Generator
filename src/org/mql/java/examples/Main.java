package org.mql.java.examples;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
		
		exp02(binPath);
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
	
	void exp01(String binPath) {
		try {
			ProjectParser projectParser = new ProjectParser(binPath);
			logger.info(projectParser.getProject().toString());
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
	}
	
	void exp02(String binPath) {
		try {
			new ProjectParser(binPath);
			
			new ProjectDOMParser();
			
			JProject jProject = new JProject(Project.getInstance());
			JScrollPane panelPane = new JScrollPane();
			panelPane.getViewport().add((JPanel) jProject.draw());
			
			setContentPane(panelPane);
			
			config();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warning(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		new Main(args[0]);
	}
}
