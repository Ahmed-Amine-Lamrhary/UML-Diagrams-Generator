package org.mql.java.ui.swing.uml;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import org.mql.java.models.UMLRelation;

public class JUMLRelation extends JPanel implements Movable {
	private static final long serialVersionUID = 1L;
	
	private UMLRelation umlRelation;
	private JProject jProject;
	
	private Point p1, p2;
	
	public JUMLRelation(UMLRelation umlRelation, JProject jProject) {
		this.umlRelation = umlRelation;
		this.jProject = jProject;
		
		// setOpaque(false);
		setBackground(Color.red);
		setSize(500, 500);
		drawRelation();
	}
	
	private void drawRelation() {
		JUMLClassifier childJumlClassifier = jProject.getJumlClassifier(umlRelation.getChild());
		JUMLClassifier parentJumlClassifier = jProject.getJumlClassifier(umlRelation.getParent());
				
		p1 = new Point(childJumlClassifier.getX(), childJumlClassifier.getY());
		p2 = new Point(parentJumlClassifier.getX(), parentJumlClassifier.getY());
		
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		

		g.setColor(Color.black);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
	}

	@Override
	public void move(MouseEvent e) {
		
	}

}
