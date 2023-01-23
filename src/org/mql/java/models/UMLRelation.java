package org.mql.java.models;

import org.mql.java.enums.RelationType;

public class UMLRelation {
	private UMLClassifier child;
	private UMLClassifier parent;
	private RelationType type;

	public UMLRelation(UMLClassifier child, UMLClassifier parent, RelationType type) {
		super();
		this.child = child;
		this.parent = parent;
		this.type = type;
	}
	
	public UMLClassifier getChild() {
		return child;
	}
	
	public UMLClassifier getParent() {
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
