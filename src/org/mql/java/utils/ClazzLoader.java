package org.mql.java.utils;

import java.net.URL;
import java.net.URLClassLoader;

public class ClazzLoader {
	public static Class<?> forName(String projectPath, String className) {
		try {
			URLClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[] {
					new URL("file:///"+projectPath+"/bin/")
			});
			
			return urlClassLoader.loadClass(className);
		} catch (Exception e) {
			System.out.println("Class " + className + " not found");
			return null;
		}

	}
}
