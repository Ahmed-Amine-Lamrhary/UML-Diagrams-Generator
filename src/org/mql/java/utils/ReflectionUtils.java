package org.mql.java.utils;

import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLClass;
import org.mql.java.models.UMLOperation;

public class ReflectionUtils {

	public static boolean isOperationParameter(String parameterTypeName, UMLOperation operation) {
		return false;
	}
	
	public static boolean isClassAttribute(UMLClass umlClass, UMLAttribute attribute) {
		return false;
	}
	
	public static boolean isFinalClassAttribute(UMLClass umlClass, UMLAttribute attribute) {
		return (isClassAttribute(umlClass, attribute) && attribute.isFinal());
	}
}
