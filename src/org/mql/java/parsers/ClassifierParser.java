package org.mql.java.parsers;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Vector;

import org.mql.java.enums.Visibility;
import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLClass;
import org.mql.java.models.UMLClassifier;
import org.mql.java.models.UMLEnum;
import org.mql.java.models.UMLInterface;
import org.mql.java.models.UMLOperation;
import org.mql.java.utils.ClasseLoader;
import org.mql.java.utils.StringResolver;

public class ClassifierParser implements Parser {
	private UMLClassifier classifier;
	private Class<?> clazz;
	
	public ClassifierParser(File file) throws Exception {
		parse(file);
	}
	
	private Visibility getVisibility(int modifiers) {
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
	
	private boolean isAbstract(int modifiers) {
		String modifiersString = Modifier.toString(modifiers);
		return modifiersString.contains("abstract");
	}
	
	private boolean isStatic(int modifiers) {
		String modifiersString = Modifier.toString(modifiers);
		return modifiersString.contains("static");
	}
	
	private void loadUMLAttributes() {
		for (Field field : clazz.getDeclaredFields()) {
			int modifiers = field.getModifiers();
			
			Visibility visibility = getVisibility(modifiers);
			String name = field.getName();
			String type = field.getType().getSimpleName();
			
			classifier.addUMLAttribute(new UMLAttribute(visibility, name, type, isStatic(modifiers)));
		}
	}
	
	private void loadUMLOperations() {		
		for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
			Visibility visibility = getVisibility(constructor.getModifiers());

			UMLOperation umlOperation = new UMLOperation(visibility, clazz.getSimpleName());
			umlOperation.setParameters(getUMLParameters(constructor.getParameters()));
			
			classifier.addUMLOperation(umlOperation);
		}
		
		for (Method method : clazz.getDeclaredMethods()) {
			int modifiers = method.getModifiers();
			Visibility visibility = getVisibility(modifiers);
			String name = method.getName();
			String type = method.getReturnType().getSimpleName();

			UMLOperation umlOperation = new UMLOperation(visibility, name, type, isStatic(modifiers));
			umlOperation.setParameters(getUMLParameters(method.getParameters()));
			
			classifier.addUMLOperation(umlOperation);
		}		
	}
	
	private List<UMLAttribute> getUMLParameters(Parameter[] parameters) {
		List<UMLAttribute> umlParameters = new Vector<>();
		
		for (Parameter p : parameters) {
			umlParameters.add(new UMLAttribute(p.getName(), p.getType().getName()));
		}
		
		return umlParameters;
	}
	
	public UMLClassifier getClassifier() {
		return classifier;
	}
	
	@Override
	public void parse(File file) throws Exception {
		String binPath = StringResolver.binPath(file);
		String classifierName = StringResolver.fileName(file);
					
		try {
			ClasseLoader loader = new ClasseLoader(binPath);
			clazz = loader.loadClass(classifierName);	
		} catch (Exception e) {
			throw e;
		}
					
		if (clazz.isInterface()) {
			classifier = new UMLInterface(clazz.getSimpleName());
		}
		else if (clazz.isEnum()) {
			classifier = new UMLEnum(clazz.getSimpleName());
		}
		else {
			classifier = new UMLClass(clazz.getSimpleName(), isAbstract(clazz.getModifiers()));
		}
		
		loadUMLAttributes();
		loadUMLOperations();
	}
}
