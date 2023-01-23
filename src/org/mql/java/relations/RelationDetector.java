package org.mql.java.relations;

import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.mql.java.enums.RelationType;
import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLClass;
import org.mql.java.models.UMLInterface;
import org.mql.java.models.UMLMember;
import org.mql.java.models.UMLModel;
import org.mql.java.models.UMLOperation;
import org.mql.java.models.UMLRelation;
import org.mql.java.utils.ReflectionUtils;

public class RelationDetector {
	private static Logger logger = Logger.getLogger(RelationDetector.class.getName());
	
	public List<UMLRelation> parse(UMLModel parent, UMLModel child) {

		if (parent != null && child != null) {
			logger.info("detecting relation between " + parent.getSimpleName() + " and " + child.getSimpleName());
			List<UMLRelation> relations = new Vector<>();
			
			if (!parent.equals(child)) {
				UMLRelation dependency = detectDependency(parent, child);
				if (dependency != null) {
					logger.info("found dependency between " + parent.getSimpleName() + " and " + child.getSimpleName());					
					relations.add(dependency);
				}
				
				UMLRelation association = detectAssociation(parent, child);
				if (association != null) {
					logger.info("found association between " + parent.getSimpleName() + " and " + child.getSimpleName());										
					relations.add(association);
				}
				
				UMLRelation aggregation = detectAggregation(parent, child, "");
				if (aggregation != null) {
					logger.info("found aggregation between " + parent.getSimpleName() + " and " + child.getSimpleName());										
					relations.add(aggregation);
				}
				
				UMLRelation composition = detectComposition(parent, child);
				if (composition != null) {
					logger.info("found composition between " + parent.getSimpleName() + " and " + child.getSimpleName());										
					relations.add(composition);
				}
				
				UMLRelation realization = detectRealization(parent, child);
				if (realization != null) {
					logger.info("found realization between " + parent.getSimpleName() + " and " + child.getSimpleName());										
					relations.add(realization);
				}
				
				UMLRelation generalization = detectGeneralization(parent, child);
				if (generalization != null) {
					logger.info("found generalization between " + parent.getSimpleName() + " and " + child.getSimpleName());										
					relations.add(generalization);
				}
			}
			
			return relations;
		}
		return null;
	}
	
	private UMLRelation detectDependency(UMLModel parent, UMLModel child) {
		UMLRelation dependency = null;
		
		for (UMLMember member : parent.getUmlMembers()) {
			if (member instanceof UMLOperation) {
				UMLOperation operation = (UMLOperation) member;
				if (!operation.isConstructor()) {
					for (String parameter : operation.getParameters()) {
						if (ReflectionUtils.isOperationParameter(parameter, operation)) {
							dependency = new UMLRelation(parent, child, RelationType.DEPENDENCY);
						}
					}
				}
			}
		}
		
		return dependency;
	}
	
	private UMLRelation detectAssociation(UMLModel parent, UMLModel child) {
		UMLRelation association = null;
		
		if (parent instanceof UMLClass && child instanceof UMLClass) {
			for (UMLMember member : parent.getUmlMembers()) {
				if (member instanceof UMLAttribute) {
					// found issue
					if (ReflectionUtils.isClassAttribute((UMLClass) child, (UMLAttribute) member)) {
						// is class attribute
						association = new UMLRelation(child, parent, RelationType.ASSOCIATION);
					}
				}
			}
		}
		
		return association;
	}
	
	private UMLRelation detectAggregation(UMLModel parent, UMLModel child, String parameter) {
		UMLRelation aggregation = null;

		if (detectAssociation(parent, child) != null) {
			for (UMLMember member : parent.getUmlMembers()) {
				if (member instanceof UMLOperation) {
					UMLOperation operation = (UMLOperation) member;
					if (operation.isConstructor()) {
						for (String param : operation.getParameters()) {
							if (ReflectionUtils.isOperationParameter(param, operation)) {
								// attribute is operation parameter
								parameter = param;
								aggregation = new UMLRelation(parent, child, RelationType.AGGREGATION);
							}
						}
					}
				}
			}
		}
		
		return aggregation;
	}
	
	private UMLRelation detectComposition(UMLModel parent, UMLModel child) {
		UMLRelation composition = null;
		
		String parameter = "";
		
		if (detectAggregation(parent, child, parameter) != null) {
			for (UMLMember member : parent.getUmlMembers()) {
				if (member instanceof UMLAttribute) {
					if (ReflectionUtils.isFinalClassAttribute((UMLClass) child, (UMLAttribute) member)) {
						// final attribute and constructor parameter
						composition = new UMLRelation(child, parent, RelationType.COMPOSITION);
					}
				}
			}
		}
		else if (parent.getName().contains(child.getName())) {
			// inner class
			composition = new UMLRelation(child, parent, RelationType.COMPOSITION);
		}
		
		return composition;
	}
	
	private UMLRelation detectRealization(UMLModel parent, UMLModel child) {
		UMLRelation realization = null;
		
		if (parent instanceof UMLClass && child instanceof UMLInterface) {
			UMLClass clazz = (UMLClass) parent;
			if (clazz.getImplementedInterfaces().contains(child.getName())) {
				realization = new UMLRelation(parent, child, RelationType.REALIZATION);
			}
		}
		
		return realization;
	}
	
	private UMLRelation detectGeneralization(UMLModel parent, UMLModel child) {
		UMLRelation generalization = null;
		
		if (parent.getMotherModelName().equals(child.getName())) {
			generalization = new UMLRelation(parent, child, RelationType.GENERALIZATION);
		}
		
		return generalization;
	}
}
 