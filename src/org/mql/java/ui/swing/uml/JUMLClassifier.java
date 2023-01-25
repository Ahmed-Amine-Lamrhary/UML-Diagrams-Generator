package org.mql.java.ui.swing.uml;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLMember;
import org.mql.java.models.UMLClassifier;
import org.mql.java.models.UMLConstant;
import org.mql.java.models.UMLEnum;
import org.mql.java.models.UMLInterface;
import org.mql.java.models.UMLOperation;
import org.mql.java.ui.swing.BoxPanel;
import org.mql.java.ui.swing.Label;

public class JUMLClassifier extends JPanel implements Movable {
	private static final long serialVersionUID = 1L;

	private Color color;
	
	private UMLClassifier classifier;
	
	private TitlePanel titlePanel;
	private SectionPanel attributesPanel;
	private SectionPanel operationsPanel;
	
	private int eX, eY;
	
	private class CustomMouseListener implements MouseListener {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			eX = e.getX();
			eY = e.getY();
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
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
	
	public JUMLClassifier(UMLClassifier classifier) {
		this(classifier, Color.BLACK);
	}
	
	public JUMLClassifier(UMLClassifier classifier, Color color) {
		this.classifier = classifier;
		this.color = color;
		
		setLayout(new BorderLayout(0, 10));
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, color));

		drawTitlePanel();
		
		if (classifier instanceof UMLEnum) {
			drawConstantsPanel();
		} else {
			drawAttributesPanel();
			drawOperationsPanel();			
		}
		
		if (classifier instanceof UMLInterface) {
			setBackground(new Color(184, 249, 168));			
		}
		else if (classifier instanceof UMLEnum) {
			setBackground(new Color(209, 166, 253));
		}
		else {
			setBackground(Color.white);
		}
		
		setSize(getPreferredSize());
		
		addMouseListener(new CustomMouseListener());
		addMouseMotionListener(new CustomMouseMotionListener());
	}

	private void drawTitlePanel() {
		titlePanel = new TitlePanel();
		add(titlePanel, BorderLayout.NORTH);
	}
	
	private void drawAttributesPanel() {
		attributesPanel = new SectionPanel();
				
		for (UMLMember umlMember : classifier.getUmlMembers()) {
			if (umlMember instanceof UMLAttribute) {
				attributesPanel.add(new JUMLMember(umlMember));
			}
		}
		
		add(attributesPanel, BorderLayout.CENTER);
	}
	
	private void drawOperationsPanel() {
		operationsPanel = new SectionPanel();
		
		for (UMLMember umlMember : classifier.getUmlMembers()) {
			if (umlMember instanceof UMLOperation) {
				operationsPanel.add(new JUMLMember(umlMember));
			}
		}
		
		add(operationsPanel, BorderLayout.SOUTH);
	}
	

	private void drawConstantsPanel() {
		attributesPanel = new SectionPanel();
				
		for (UMLMember umlMember : classifier.getUmlMembers()) {
			if (umlMember instanceof UMLConstant) {
				attributesPanel.add(new JUMLMember(umlMember));
			}
		}

		add(attributesPanel);
	}
	
	private class TitlePanel extends BoxPanel {
		private static final long serialVersionUID = 1L;

		public TitlePanel() {		
			setBorderBottom(1);

			if (classifier instanceof UMLInterface) {
				add(new Label("<<interface>>"));
			}
			else if (classifier instanceof UMLEnum) {
				Label titleLabel = new Label("<<enum>>");
				add(titleLabel);
			}
			
			add(new Label(classifier.getSimpleName()));
			
			setOpaque(false);
		}
	}
	
	private class SectionPanel extends BoxPanel {
		private static final long serialVersionUID = 1L;
		
		public SectionPanel() {
			setBorderBottom(1);
			
			setOpaque(false);
		}
	}
	
	public UMLClassifier getClassifier() {
		return classifier;
	}
	
	@Override
	public void move(MouseEvent e) {
		setLocation((getX() + e.getX() - eX), (getY() + e.getY() - eY));
		
		// TODO : notify associated relations
	}
}
