package org.mql.java.ui.swing.uml;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import org.mql.java.models.UMLMember;
import org.mql.java.models.UMLConstant;
import org.mql.java.models.UMLOperation;
import org.mql.java.models.UMLProperty;
import org.mql.java.ui.swing.Label;

public class JUMLMember extends JPanel implements Drawable {
	private static final long serialVersionUID = 1L;

	protected UMLMember umlCharacteristic;
	
	protected Label signatureLabel;
	
	public JUMLMember(UMLMember umlCharacteristic) {
		this.umlCharacteristic = umlCharacteristic;
		
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
				signatureLabel.addText("(" + String.join(",", operation.getParameters()) + ")");
			}
			
			if (property.getSimpleType() != null) {
				signatureLabel.addText(": " + property.getSimpleType());				
			}
		}

		add(signatureLabel);
	}

	@Override
	public Object draw() {
		return this;
	}
}
