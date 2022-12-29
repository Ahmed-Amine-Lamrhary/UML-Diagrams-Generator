package org.mql.java.models;

import org.mql.java.enums.RelationshipType;

public class Relationship {
	private Model model1;
	private Model model2;
	private RelationshipType type;
	
	public Relationship(Model member1, Model member2, RelationshipType type) {		
		this.model1 = member1;
		this.model2 = member2;
		this.type = type;
	}

	public Model getMember1() {
		return model1;
	}
	
	public Model getMember2() {
		return model2;
	}
	
	public RelationshipType getType() {
		return type;
	}

	@Override
	public String toString() {
		if (type == RelationshipType.EXTENSION) {
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
