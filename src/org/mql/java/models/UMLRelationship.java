package org.mql.java.models;

import org.mql.java.enums.RelationshipType;

public class UMLRelationship {
	private UMLModel model1;
	private UMLModel model2;
	private RelationshipType type;
	
	public UMLRelationship(UMLModel model1, UMLModel model2, RelationshipType type) {		
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
	
	public RelationshipType getType() {
		return type;
	}

	@Override
	public String toString() {
		return model1.getName() + type.getSymbol() + model2.getName();
	}
}
