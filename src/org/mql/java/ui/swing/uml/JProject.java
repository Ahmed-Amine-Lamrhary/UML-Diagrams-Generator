package org.mql.java.ui.swing.uml;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import org.mql.java.models.Project;
import org.mql.java.models.UMLPackage;
import org.mql.java.ui.swing.WrapLayout;

public class JProject extends JPanel implements Drawable {
	private static final long serialVersionUID = 1L;
	
	private Project project;
		
	public JProject(Project project) {
		this.project = project;
	}
	
	@Override
	public Object draw() {
		setLayout(null);
		
		JUMLPackage jumlPackage;
				
		for (UMLPackage umlPackage : project.getPackages()) {
			int x = 1, y = 1;
			
			jumlPackage = new JUMLPackage(umlPackage);
			JPanel p = (JPanel) jumlPackage.draw();
			p.setLocation(x, y);
			add(p);
		}
		
		return this;
	}
}
