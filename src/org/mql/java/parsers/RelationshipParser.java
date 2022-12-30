package org.mql.java.parsers;

import java.util.Set;

import org.mql.java.models.Project;
import org.mql.java.models.UMLRelationship;

public class RelationshipParser {
	private Project project;
	
	private Set<UMLRelationship> packagesRelations;
	private Set<UMLRelationship> classesRelations;
	
	public RelationshipParser(Project project) {
		this.project = project;
	}
	
	/*
	 for (Relationship relationship : relationships) {
			if (relationship.getMember1().getSuperPackage() != null && !relationship.getMember1().getSuperPackage().getName().equals(name)) {
				relationships.add(new Relationship(this, relationship.getMember2().getSuperPackage(), RelationshipType.DEPENDENCY));
			}
			else if (relationship.getMember2().getSuperPackage() != null && !relationship.getMember2().getSuperPackage().getName().equals(name)) {
				relationships.add(new Relationship(this, relationship.getMember1().getSuperPackage(), RelationshipType.DEPENDENCY));
			}
		}
	 */

}
