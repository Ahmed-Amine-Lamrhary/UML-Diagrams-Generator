package org.mql.java.models;

import org.mql.java.enums.RelationType;

public class UMLRelation {
	private UMLElement element1;
	private UMLElement element2;
	private RelationType type;
	
	public UMLRelation(UMLElement element1, UMLElement element2, RelationType type) {		
		this.element1 = element1;
		this.element2 = element2;
		this.type = type;
	}

	public UMLElement getElement1() {
		return element1;
	}
	
	public UMLElement getElement2() {
		return element2;
	}
	
	public RelationType getType() {
		return type;
	}
	
	public void setElement1(UMLElement element1) {
		this.element1 = element1;
	}
	
	public void setElement2(UMLElement element2) {
		this.element2 = element2;
	}
	
	public void setType(RelationType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return element1.getName() + type.getSymbol() + element2.getName();
	}
}
