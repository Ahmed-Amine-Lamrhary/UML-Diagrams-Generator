package org.mql.java.parsers;

import org.mql.java.models.UMLAnnotation;
import org.mql.java.utils.ClasseLoader;

public class AnnotationParser {	
	private UMLAnnotation annotation;
	
	public AnnotationParser(String projectPath, String annotationName) {
		this(ClasseLoader.forName(projectPath, annotationName));
	}
	
	public AnnotationParser(Class<?> clazz) {
		annotation = new UMLAnnotation(clazz.getName());
	}
	
	public UMLAnnotation getAnnotation() {
		return annotation;
	}
}
