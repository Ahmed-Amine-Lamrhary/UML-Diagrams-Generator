package org.mql.java.utils;

import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLClass;
import org.mql.java.models.UMLMember;
import org.mql.java.models.UMLOperation;

public class ReflectionUtils {

	public static boolean isOperationParameter(String parameterTypeName, UMLOperation operation) {
		for (String paramTypeName : operation.getParameters()) {
			if (paramTypeName.equals(parameterTypeName))
				return true;
		}
		
		return false;
	}
	
	public static boolean isClassAttribute(UMLClass umlClass, UMLAttribute attribute) {
		for (UMLMember member : umlClass.getUmlMembers()) {
			if (member instanceof UMLAttribute) {
				UMLAttribute localAttribute = (UMLAttribute) member;
				if (localAttribute.getName().equals(attribute))
					return true;
			}
		}
		
		return false;
	}
	
	public static boolean isFinalClassAttribute(UMLClass umlClass, UMLAttribute attribute) {
		return (isClassAttribute(umlClass, attribute) && attribute.isFinal());
	}
}
