package org.mql.java.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Vector;

import org.mql.java.enums.ModelType;
import org.mql.java.enums.Visibility;
import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLModel;
import org.mql.java.models.UMLOperation;
import org.mql.java.models.UMLParameter;
import org.mql.java.utils.ClasseLoader;

public class ModelParser {
	private UMLModel model;
	private Class<?> clazz;
	
	public ModelParser(String projectPath, String modelName) {
		try {
			ClasseLoader loader = new ClasseLoader(projectPath);
			clazz = loader.loadClass(modelName);
			
			if (clazz.isAnnotation()) {
				loadAnnotation();
			}
			else if (clazz.isInterface()) {
				loadInterface();
			}
			else if (clazz.isEnum()) {
				loadEnum();
			}
			else {
				loadClass();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	private void loadClass() {
		model = new UMLModel(clazz.getSimpleName(), ModelType.CLASS);
		
		model.setFields(getUMLAttributes());
		model.setMethods(getUMLOperationsFMethods());
		model.setConstructors(getUMLOperationsFConstructors());
	}
	
	private void loadInterface() {
		model = new UMLModel(clazz.getName(), ModelType.INTERFACE);
		
		model.setFields(getUMLAttributes());
		model.setMethods(getUMLOperationsFMethods());
	}
	
	private void loadAnnotation() {
		model = new UMLModel(clazz.getName(), ModelType.ANNOTATION);
		
		model.setMethods(getUMLOperationsFMethods());
	}
	
	private void loadEnum() {
		model = new UMLModel(clazz.getName(), ModelType.ENUM);
		
		List<UMLAttribute> umlAttributes = new Vector<>();
		
		for (Object value : clazz.getEnumConstants()) {
			umlAttributes.add(new UMLAttribute(value.toString()));
		}
		
		model.setFields(umlAttributes);
	}
	
	public Visibility getVisibilityFromModifiers() {
		String modifiersString = Modifier.toString(clazz.getModifiers());

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
	
	private boolean isStatic() {
		String modifiersString = Modifier.toString(clazz.getModifiers());
		return modifiersString.contains("static");
	}
	
	private List<UMLAttribute> getUMLAttributes() {
		List<UMLAttribute> UMLFields = new Vector<>();
		
		for (Field field : clazz.getDeclaredFields()) {
			Visibility visibility = getVisibilityFromModifiers();
			String name = field.getName();
			String type = field.getType().getSimpleName();
			
			UMLFields.add(new UMLAttribute(visibility, name, type, isStatic()));
		}
		
		return UMLFields;
	}
	
	private List<UMLOperation> getUMLOperationsFMethods() {
		List<UMLOperation> UMLOperations = new Vector<>();
		
		for (Method method : clazz.getDeclaredMethods()) {
			Visibility visibility = getVisibilityFromModifiers();
			String name = method.getName();
			String type = method.getReturnType().getSimpleName();

			UMLOperation umlOperation = new UMLOperation(visibility, name, type, isStatic());
			umlOperation.setParameters(getUMLParameters(method.getParameters()));
			
			UMLOperations.add(umlOperation);
		}
		
		return UMLOperations;
	}
	
	private List<UMLOperation> getUMLOperationsFConstructors() {
		List<UMLOperation> UMLOperations = new Vector<>();
		
		for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
			Visibility visibility = getVisibilityFromModifiers();

			UMLOperation umlOperation = new UMLOperation(visibility, clazz.getSimpleName());
			umlOperation.setParameters(getUMLParameters(constructor.getParameters()));
			
			UMLOperations.add(umlOperation);
		}
		
		return UMLOperations;
	}
	
	private List<UMLParameter> getUMLParameters(Parameter[] parameters) {
		List<UMLParameter> umlParameters = new Vector<>();
		
		for (Parameter p : parameters) {
			umlParameters.add(new UMLParameter(p.getName(), p.getType().getName()));
		}
		
		return umlParameters;
	}
	
	public UMLModel getModel() {
		return model;
	}
}
