package org.mql.java.utils;

import java.net.URL;
import java.net.URLClassLoader;

public class ClasseLoader {
	private URLClassLoader loader;
	
	public ClasseLoader(String path) throws Exception {
		loader = URLClassLoader.newInstance(new URL[] {
				new URL("file:///"+path+"/")
		});
	}

	public Class<?> loadClass(String className) throws Exception {
		return loader.loadClass(className);
	}
}
