package org.mql.java.parsers;

import java.io.File;
import java.util.List;

import java.util.Vector;

import org.mql.java.models.UMLAnnotation;
import org.mql.java.models.UMLClass;
import org.mql.java.models.UMLEnumeration;
import org.mql.java.models.UMLInterface;
import org.mql.java.models.UMLPackage;
import org.mql.java.utils.ClasseLoader;

public class PackageParser {
	private UMLPackage packageM;
		
	public PackageParser(String projectPath, String packageName) {		
		packageM = new UMLPackage(packageName);
		
		String packagePath = packageName.replace(".", "/");
		File dir = new File(projectPath + "/bin/" + packagePath);
		File f[] = dir.listFiles();
		
		List<UMLPackage> packages = new Vector<UMLPackage>();
		List<UMLAnnotation> annotations = new Vector<UMLAnnotation>();
		List<UMLClass> classes = new Vector<UMLClass>();
		List<UMLInterface> interfaces = new Vector<UMLInterface>();
		List<UMLEnumeration> enumerations = new Vector<UMLEnumeration>();
		
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
	
	public UMLPackage getPackageM() {
		return packageM;
	}
}
