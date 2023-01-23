package org.mql.java.ui.swing.uml;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import org.mql.java.models.Project;
import org.mql.java.models.UMLClassifier;
import org.mql.java.models.UMLPackage;
import org.mql.java.models.UMLRelation;

public class JProject extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Project project;
	private List<JUMLPackage> jumlPackages;
	private List<JUMLRelation> jumlRelations;
		
	public JProject(Project project) {
		this.project = project;
		jumlPackages = new Vector<>();
		jumlRelations = new Vector<>();
		
		setLayout(null);
		
		drawPackages();
		drawRelations();
	}
	
	private void drawPackages() {
		JUMLPackage jumlPackage;
		for (UMLPackage umlPackage : project.getPackages()) {
			int x = 1, y = 1;
			
			jumlPackage = new JUMLPackage(umlPackage);
			jumlPackage.setLocation(x, y);
			add(jumlPackage);
			
			jumlPackages.add(jumlPackage);
		}
	}
	
	private void drawRelations() {
		JUMLRelation jumlRelation;
		for (UMLRelation umlRelation : project.getRelations()) {
			jumlRelation = new JUMLRelation(umlRelation, this);
			
			add(jumlRelation);
			jumlRelations.add(jumlRelation);
		}
	}
	
	public JUMLClassifier getJumlClassifier(UMLClassifier umlClassifier) {
		for (JUMLPackage jumlPackage : jumlPackages) {
			for (JUMLClassifier jumlClassifier : jumlPackage.getJumlClassifiers()) {
				if (jumlClassifier.getClassifier().getName().equals(umlClassifier.getName())) {
					return jumlClassifier;
				}
			}
		}
		
		return null;
	}
}
