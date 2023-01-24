package org.mql.java.ui.swing.uml;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import org.mql.java.models.UMLMember;
import org.mql.java.models.UMLConstant;
import org.mql.java.models.UMLOperation;
import org.mql.java.models.UMLProperty;
import org.mql.java.ui.swing.Label;

public class JUMLMember extends JPanel {
	private static final long serialVersionUID = 1L;

	protected UMLMember umlMember;
	
	protected Label signatureLabel;
	
	public JUMLMember(UMLMember umlCharacteristic) {
		this.umlMember = umlCharacteristic;
		
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		signatureLabel = new Label();
		
		if (umlCharacteristic instanceof UMLConstant) {
			signatureLabel.addText(umlCharacteristic.getName());
		}
		else {
			UMLProperty property = (UMLProperty) umlCharacteristic;
			
			if (property.isStatic()) {
				signatureLabel.setUnderline();
			}
			
			signatureLabel.addText(property.getVisibility().getSymbol());
			signatureLabel.addText(property.getName());
			
			if (property instanceof UMLOperation) {
				UMLOperation operation = (UMLOperation) property;
				signatureLabel.addText("(");
				
				for (int i = 0; i < operation.getParameters().size(); i++) {
					signatureLabel.addText(operation.getParameters().get(i).getSimpleType());
					if (i < operation.getParameters().size()-1) {
						signatureLabel.addText(", ");
					}
				}

				signatureLabel.addText(")");
			}
			
			if (property.getSimpleType() != null) {
				signatureLabel.addText(": " + property.getSimpleType());				
			}
		}

		add(signatureLabel);
	}
}
