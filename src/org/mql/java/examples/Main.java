package org.mql.java.examples;

import org.mql.java.parsers.PackageParser;

public class Main {

	public Main() {
		PackageParser packageParser = new PackageParser("org.mql.java.parsers");
		
		System.out.println(packageParser.getClasses());
	}

	public static void main(String[] args) {
		new Main();
	}
}
