/**
 * 
 */
package org.mql.java.models;

/**
 * @author Amine Lamrhary
 *
 * Jan 23, 2023
 */
public class UMLParameter {
	private String type;
	
	public UMLParameter(String type) {
		this.type = type;
	}
	
	public String getSimpleType() {
		return type;
		// if (type == null) return null;
		// return type.substring(type.lastIndexOf(".") + 1);
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
