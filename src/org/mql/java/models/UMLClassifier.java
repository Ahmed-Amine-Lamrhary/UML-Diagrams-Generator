package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public abstract class UMLClassifier {
	protected String name;
	protected String simpleName;
	protected List<UMLMember> umlMembers;

	public UMLClassifier(String name, String simpleName) {
		this.name = name;
		this.simpleName = simpleName;
		umlMembers = new Vector<>();
	}
	
	public void addUMLMember(UMLMember member) {
		umlMembers.add(member);
	}
	
	public List<UMLMember> getUmlMembers() {
		return umlMembers;
	}
	
	public String getSimpleName() {
		return simpleName;
	}
	
	public String getName() {
		return name;
	}
}
