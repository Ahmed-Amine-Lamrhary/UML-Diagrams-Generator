package org.mql.java.models;

import org.mql.java.enums.RelationType;

public class UMLRelation {
	private UMLModel model1;
	private UMLModel model2;
	private RelationType type;
	
	public UMLRelation(UMLModel model1, UMLModel model2, RelationType type) {		
		this.model1 = model1;
		this.model2 = model2;
		this.type = type;
	}

	public UMLModel getModel1() {
		return model1;
	}
	
	public UMLModel getModel2() {
		return model2;
	}
	
	public RelationType getType() {
		return type;
	}

	@Override
	public String toString() {
		return model1.getName() + type.getSymbol() + model2.getName();
	}
}
