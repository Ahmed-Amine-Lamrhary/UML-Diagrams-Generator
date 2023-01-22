package org.mql.java.parsers;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.logging.Logger;

import org.mql.java.enums.Visibility;
import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLClass;
import org.mql.java.models.UMLClassifier;
import org.mql.java.models.UMLConstant;
import org.mql.java.models.UMLEnum;
import org.mql.java.models.UMLInterface;
import org.mql.java.models.UMLOperation;
import org.mql.java.utils.ClasseLoader;
import org.mql.java.utils.StringResolver;

public class ClassifierParser implements Parser {
	private Logger logger = Logger.getLogger(getClass().getName());
	private UMLClassifier classifier;
	private Class<?> clazz;
	
	public ClassifierParser(File file) throws Exception {
		logger.info("Parsing classifier : " + file.getAbsolutePath());
		parse(file);
		logger.info("Classifier parsed");
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
		return Modifier.toString(modifiers).contains("abstract");
	}
	
	private boolean isStatic(int modifiers) {
		return Modifier.toString(modifiers).contains("static");
	}
	
	private void loadUMLProperty(Member member) {
		int modifiers = member.getModifiers();
		Visibility visibility = getVisibility(modifiers);
		String name = member instanceof Constructor ? clazz.getSimpleName() : member.getName();
		
		if (member instanceof Field) {
			Class<?> type = ((Field) member).getType();
			classifier.addUMLMember(new UMLAttribute(name, visibility, type, isStatic(modifiers)));
		}
		else {
			UMLOperation umlOperation;
			
			if (member instanceof Constructor) {
				umlOperation = new UMLOperation(name, visibility);
			}
			else {
				Class<?> type = ((Method) member).getReturnType();
				umlOperation = new UMLOperation(name, visibility, type, isStatic(modifiers));
			}			

			for (Parameter p : ((Executable) member).getParameters()) {
				umlOperation.addParameter(p.getType().getSimpleName());
			}
			
			classifier.addUMLMember(umlOperation);
		}				
	}
	
	private void loadUMLAttributes() {		
		for (Field field : clazz.getDeclaredFields()) {			
			loadUMLProperty(field);
		}
	}
	
	private void loadUMLOperations() {
		for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
			loadUMLProperty(constructor);
		}
		
		for (Method method : clazz.getDeclaredMethods()) {
			loadUMLProperty(method);
		}
	}
	
	private void loadUMLConstants() {
		for (Object constant : clazz.getEnumConstants()) {
			classifier.addUMLMember(new UMLConstant(constant.toString()));
		}
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
			
			if (clazz.isInterface()) {
				classifier = new UMLInterface(clazz.getName(), clazz.getSimpleName());
			}
			else if (clazz.isEnum()) {
				classifier = new UMLEnum(clazz.getName(), clazz.getSimpleName());
			}
			else {
				classifier = new UMLClass(clazz.getName(), clazz.getSimpleName(), isAbstract(clazz.getModifiers()));
			}
			
			if (!(classifier instanceof UMLEnum)) {
				loadUMLAttributes();
				loadUMLOperations();
			} else {
				loadUMLConstants();
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
