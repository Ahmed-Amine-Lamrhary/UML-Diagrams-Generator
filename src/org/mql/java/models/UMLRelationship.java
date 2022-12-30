package org.mql.java.models;

import org.mql.java.enums.RelationshipType;

public class UMLRelationship {
	private UMLModel model1;
	private UMLModel model2;
	private RelationshipType type;
	
	public UMLRelationship(UMLModel member1, UMLModel member2, RelationshipType type) {		
		this.model1 = member1;
		this.model2 = member2;
		this.type = type;
	}

	public UMLModel getMember1() {
		return model1;
	}
	
	public UMLModel getMember2() {
		return model2;
	}
	
	public RelationshipType getType() {
		return type;
	}

	@Override
	public String toString() {
		if (type == RelationshipType.INHERITANCE) {
			return model1.getName() + " --|> " + model2.getName() + "\n";
		}
		else if (type == RelationshipType.COMPOSITION) {
			return model1.getName() + " <>-- " + model2.getName() + "\n";
		}
		else {
			return model1.getName() + "-->" + model2.getName() + "\n";
		}
	}
}
