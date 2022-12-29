package org.mql.java.parsers;

import org.mql.java.models.Annotation;
import org.mql.java.utils.ClasseLoader;

public class AnnotationParser {	
	private Annotation annotation;
	
	public AnnotationParser(String projectPath, String annotationName) {
		this(ClasseLoader.forName(projectPath, annotationName));
	}
	
	public AnnotationParser(Class<?> clazz) {
		annotation = new Annotation(clazz.getName());
	}
	
	public Annotation getAnnotation() {
		return annotation;
	}
}
