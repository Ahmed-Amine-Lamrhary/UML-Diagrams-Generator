package org.mql.java.utils;

import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLClass;
import org.mql.java.models.UMLMember;
import org.mql.java.models.UMLModel;
import org.mql.java.models.UMLOperation;
import org.mql.java.models.UMLParameter;

public class RelationUtils {

	public static boolean parameterInAtLeastOneConstructor(String parameterTypeName, UMLModel parent) {
		for (UMLMember member : parent.getUmlMembers()) {
			if (member instanceof UMLOperation) {
				UMLOperation operation = (UMLOperation) member;
				if (RelationUtils.isConstructorParameter(parameterTypeName, operation)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean isConstructorParameter(String parameterTypeName, UMLOperation operation) {
		return isOperationParameter(parameterTypeName, operation) && operation.isConstructor();
	}
	
	public static boolean isMethodParameter(String parameterTypeName, UMLOperation operation) {
		return isOperationParameter(parameterTypeName, operation) && !operation.isConstructor();
	}
	
	private static boolean isOperationParameter(String parameterTypeName, UMLOperation operation) {
		for (UMLParameter param : operation.getParameters()) {
			if (param.getType().equals(parameterTypeName))
				return true;
		}
		
		return false;
	}
	
	public static boolean isClassAttribute(UMLClass umlClass, UMLAttribute attribute) {
		for (UMLMember member : umlClass.getUmlMembers()) {
			if (member instanceof UMLAttribute) {
				UMLAttribute localAttribute = (UMLAttribute) member;

				if (localAttribute.getType().contains(attribute.getType())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	public static boolean isFinalClassAttribute(UMLClass umlClass, UMLAttribute attribute) {
		return (isClassAttribute(umlClass, attribute) && attribute.isFinal());
	}
	
	public static UMLAttribute childInParentAttributes(UMLModel child, UMLModel parent) {
		for (UMLMember member : parent.getUmlMembers()) {
			if (member instanceof UMLAttribute) {
				UMLAttribute attribute = (UMLAttribute) member;
				if (attribute.getType().contains(child.getName()))
					return attribute;
			}
		}
		
		return null;
	}
}
