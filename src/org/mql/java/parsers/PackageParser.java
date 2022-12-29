package org.mql.java.parsers;

import java.io.File;
import java.util.List;

import java.util.Vector;

import org.mql.java.models.Annotation;
import org.mql.java.models.Classe;
import org.mql.java.models.Enumeration;
import org.mql.java.models.Interface;
import org.mql.java.models.PackageM;
import org.mql.java.utils.ClasseLoader;

public class PackageParser {
	private PackageM packageM;
		
	public PackageParser(String projectPath, String packageName) {		
		packageM = new PackageM(packageName);
		
		String packagePath = packageName.replace(".", "/");
		File dir = new File(projectPath + "/bin/" + packagePath);
		File f[] = dir.listFiles();
		
		List<PackageM> packages = new Vector<PackageM>();
		List<Annotation> annotations = new Vector<Annotation>();
		List<Classe> classes = new Vector<Classe>();
		List<Interface> interfaces = new Vector<Interface>();
		List<Enumeration> enumerations = new Vector<Enumeration>();
		
		if (f != null) {
			for (int i = 0; i < f.length; i++) {
				String name = f[i].getName().replace(".class", "");
				String fullname = packageName + "." + name;

				if (f[i].isFile() && f[i].getName().endsWith(".class")) {
					Class<?> classFile = ClasseLoader.forName(projectPath, fullname);
					
					if (classFile.isAnnotation()) {
						annotations.add(new AnnotationParser(classFile).getAnnotation());
					}
					else if (classFile.isInterface()) {
						interfaces.add(new InterfaceParser(classFile).getInterface());	
					}
					else if (classFile.isEnum()) {
						enumerations.add(new EnumParser(classFile).getEnumeration());
					}
					else {
						classes.add(new ClassParser(projectPath, classFile, true).getClasse());
					}
				}
				else if (f[i].isDirectory()) {
					packages.add(new PackageParser(projectPath, fullname).getPackageM());
				}
			}
			
			packageM.setAnnotations(annotations);
			packageM.setClasses(classes);
			packageM.setEnumerations(enumerations);
			packageM.setInterfaces(interfaces);
			packageM.setPackages(packages);
		}
	}
	
	public PackageM getPackageM() {
		return packageM;
	}
}
