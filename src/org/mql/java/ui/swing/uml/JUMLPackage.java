package org.mql.java.ui.swing.uml;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.mql.java.models.UMLClassifier;
import org.mql.java.models.UMLPackage;
import org.mql.java.ui.swing.BoxPanel;
import org.mql.java.ui.swing.Label;
import org.mql.java.ui.swing.WrapLayout;
import org.mql.java.utils.Utils;

public class JUMLPackage extends BoxPanel implements Movable {
	private static final long serialVersionUID = 1L;

	private UMLPackage umlPackage;
	
	private JPanel titlePanel;
	private JPanel classifiersPanel;
	
	private int eX, eY;
	
	private class CustomMouseListener implements MouseListener {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			eX = e.getX();
			eY = e.getY();
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    }
	
	private class CustomMouseMotionListener implements MouseMotionListener {
		@Override
		public void mouseMoved(MouseEvent e) {}
		
		@Override
		public void mouseDragged(MouseEvent e) {
            move(e);
		}
	}
	
	public JUMLPackage(UMLPackage umlPackage) {
		this.umlPackage = umlPackage;		
		
		addMouseListener(new CustomMouseListener());
		addMouseMotionListener(new CustomMouseMotionListener());
	}

	private void drawClassifiers(int padding) {
		classifiersPanel = new JPanel(null);
		
		classifiersPanel.setLayout(new WrapLayout(FlowLayout.LEFT, padding, padding));
		classifiersPanel.setBorder(new LineBorder(Color.black, 1));
		classifiersPanel.setBackground(Utils.rgbColor(253, 239, 231));
		
		for (UMLClassifier classifier : umlPackage.getClassifiers()) {
			JUMLClassifier jumlClassifier = new JUMLClassifier(classifier);
			JPanel classifierPanel = (JPanel) jumlClassifier.draw();
			classifierPanel.setLocation(10, 10);
			classifiersPanel.add(classifierPanel);
		}
		
		classifiersPanel.setSize(600, 200);
		add(classifiersPanel);
	}
	
	private void drawTitle(int padding) {
		titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		titlePanel.setSize(100, 100);
		Label titleLabel = new Label(umlPackage.getName());
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT, padding, padding));	
		p.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.black));
		p.setBackground(Utils.rgbColor(253, 239, 231));
		p.add(titleLabel);
		
		titlePanel.add(p);
		
		add(titlePanel);
	}
	
	@Override
	public Object draw() {
		setOpaque(false);
		drawTitle(4);
		drawClassifiers(15);
		
		setSize(getPreferredSize());
		
		return this;
	}

	@Override
	public void move(MouseEvent e) {
		setLocation(getX() + e.getX() - eX, getY() + e.getY() - eY);
	}

}
