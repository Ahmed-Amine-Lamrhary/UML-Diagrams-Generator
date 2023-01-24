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
import org.mql.java.models.Multiplicity;
import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLClass;
import org.mql.java.models.UMLClassifier;
import org.mql.java.models.UMLConstant;
import org.mql.java.models.UMLEnum;
import org.mql.java.models.UMLInterface;
import org.mql.java.models.UMLOperation;
import org.mql.java.models.UMLParameter;
import org.mql.java.utils.ClasseLoader;
import org.mql.java.utils.ReflectionUtils;
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
	
	private boolean isFinal(int modifiers) {
		return Modifier.toString(modifiers).contains("final");
	}
	
	private void loadUMLProperty(Member member) {
		int modifiers = member.getModifiers();
		Visibility visibility = getVisibility(modifiers);
		String name = member instanceof Constructor ? clazz.getSimpleName() : member.getName();
				
		if (!member.getName().contains("$")) {
			if (member instanceof Field) {
				String type = ((Field) member).getGenericType().getTypeName();
				Multiplicity multiplicity = new Multiplicity();
				if (ReflectionUtils.isIterable((Field) member)) {
					multiplicity.setUpperBound('n');
				}
				
				UMLAttribute attribute = new UMLAttribute(name, visibility, type, isStatic(modifiers), isFinal(modifiers));
				attribute.setMultiplicity(multiplicity);
				classifier.addUMLMember(attribute);				
			}
			else {
				UMLOperation umlOperation;
				
				if (member instanceof Constructor) {
					umlOperation = new UMLOperation(name, visibility);
				}
				else {
					String type = ((Method) member).getGenericReturnType().getTypeName();
					umlOperation = new UMLOperation(name, visibility, type, isStatic(modifiers), isFinal(modifiers), false);
				}			
				
				for (Parameter p : ((Executable) member).getParameters()) {
					String pType = p.getParameterizedType().getTypeName();
					umlOperation.addParameter(new UMLParameter(pType));
				}
				
				classifier.addUMLMember(umlOperation);
			}
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
			
			String motherModelName = clazz.getSuperclass() != null ? clazz.getSuperclass().getName() : "";
			
			if (clazz.isInterface()) {
				classifier = new UMLInterface(clazz.getName(), clazz.getSimpleName(), motherModelName);
			}
			else if (clazz.isEnum()) {
				classifier = new UMLEnum(clazz.getName(), clazz.getSimpleName());
			}
			else {
				classifier = new UMLClass(clazz.getName(), clazz.getSimpleName(), isAbstract(clazz.getModifiers()), motherModelName);
				for (Class<?> interfaceClass : clazz.getInterfaces()) {
					((UMLClass) classifier).addImplementedInterface(interfaceClass.getName());
				}
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
