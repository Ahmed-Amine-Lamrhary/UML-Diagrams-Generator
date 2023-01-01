package org.mql.java.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Vector;

import org.mql.java.enums.Visibility;
import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLOperation;
import org.mql.java.models.UMLParameter;

public class Utils {

	public static Visibility getVisibilityFromModifiers(int modifiers) {
		String modifiersString = Modifier.toString(modifiers);

		if (modifiersString.contains("public")) {
			return Visibility.PUBLIC;
		}
		else if (modifiersString.contains("private")) {
			return Visibility.PRIVATE;
		}
		else if (modifiersString.contains("protected")) {
			return Visibility.PROTECTED;
		}
		else {
			return Visibility.PACKAGE;
		}
	}
	
	public static boolean isStatic(int modifiers) {
		String modifiersString = Modifier.toString(modifiers);
		return modifiersString.contains("static");
	}
	
	public static boolean isFinal(int modifiers) {
		String modifiersString = Modifier.toString(modifiers);
		return modifiersString.contains("final");
	}
	
	public static List<UMLAttribute> getUMLAttributes(Field[] fields) {
		List<UMLAttribute> UMLFields = new Vector<>();
		
		for (Field field : fields) {
			int modifiers = field.getModifiers();
			
			Visibility visibility = getVisibilityFromModifiers(modifiers);
			String name = field.getName();
			String type = field.getType().getSimpleName();
			boolean isStatic = Utils.isStatic(modifiers);
			boolean isFinal = Utils.isFinal(modifiers);
			
			UMLFields.add(new UMLAttribute(visibility, name, type, isStatic, isFinal));
		}
		
		return UMLFields;
	}
	
	public static List<UMLOperation> getUMLOperations(Method[] methods) {
		List<UMLOperation> UMLOperations = new Vector<>();
		
		for (Method method : methods) {
			int modifiers = method.getModifiers();

			Visibility visibility = getVisibilityFromModifiers(modifiers);
			String name = method.getName();
			String type = method.getReturnType().getSimpleName();
			boolean isStatic = Utils.isStatic(modifiers);
			boolean isFinal = Utils.isFinal(modifiers);

			UMLOperation umlOperation = new UMLOperation(visibility, name, type, isStatic, isFinal);
			umlOperation.setParameters(getUMLParameters(method.getParameters()));
			
			UMLOperations.add(umlOperation);
		}
		
		return UMLOperations;
	}
	
	public static List<UMLOperation> getUMLOperations(String className, Constructor<?>[] constructors) {
		List<UMLOperation> UMLOperations = new Vector<>();
		
		for (Constructor<?> constructor : constructors) {
			int modifiers = constructor.getModifiers();

			Visibility visibility = getVisibilityFromModifiers(modifiers);

			UMLOperation umlOperation = new UMLOperation(visibility, className);
			umlOperation.setParameters(getUMLParameters(constructor.getParameters()));
			
			UMLOperations.add(umlOperation);
		}
		
		return UMLOperations;
	}
	
	public static List<UMLParameter> getUMLParameters(Parameter[] parameters) {
		List<UMLParameter> umlParameters = new Vector<>();
		
		for (Parameter p : parameters) {
			umlParameters.add(new UMLParameter(p.getName(), p.getType().getName()));
		}
		
		return umlParameters;
	}
}
