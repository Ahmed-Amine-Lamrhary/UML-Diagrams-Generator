package org.mql.java.models;

import org.mql.java.enums.RelationType;

public class UMLRelation {
	private UMLModel child;
	private UMLModel parent;
	private RelationType type;

	public UMLRelation(UMLModel child, UMLModel parent, RelationType type) {
		super();
		this.child = child;
		this.parent = parent;
		this.type = type;
	}
	
	public UMLModel getChild() {
		return child;
	}
	
	public UMLModel getParent() {
		return parent;
	}
	
	public RelationType getType() {
		return type;
	}

	@Override
	public String toString() {
		return child.getName() + " " + type.getSymbol() + " " + parent.getName();
	}
}
