package org.mql.java.parsers;

import org.mql.java.utils.ClazzLoader;

public class AnnotationParser {
	private String annotationName;
	
	public AnnotationParser(String projectPath, String annotationName) {
		this(ClazzLoader.forName(projectPath, annotationName));
	}
	
	public AnnotationParser(Class<?> classe) {
		annotationName = classe.getName();
	}
	
	public String getAnnotationName() {
		return annotationName;
	}
	
	@Override
	public String toString() {
		return "Annotation : " + annotationName;
	}
}
