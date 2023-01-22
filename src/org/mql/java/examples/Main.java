package org.mql.java.examples;

import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import org.mql.java.parsers.ProjectParser;
import org.mql.java.ui.swing.uml.JProject;
import org.mql.java.ui.swing.uml.Movable;

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
			ProjectParser projectParser = new ProjectParser(binPath);
			
			JProject jProject = new JProject(projectParser.getProject());
			JScrollPane panelPane = new JScrollPane();
			panelPane.getViewport().add((JPanel) jProject.draw());
			
			panelPane.setSize(new Dimension(1000, 1000));
			
			setContentPane(panelPane);
			
			config();
		} catch (Exception e) {
			logger.warning(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		new Main(args[0]);
	}
}
