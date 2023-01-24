/**
 * 
 */
package org.mql.java.models;

/**
 * @author Amine Lamrhary
 *
 * Jan 23, 2023
 */
public class Multiplicity {
	private char lowerBound;
	private char upperBound;
	
	public Multiplicity() {
		this.lowerBound = '1';
		this.upperBound = '1';
	}
	
	public Multiplicity(char lowerBound, char upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	public void setUpperBound(char upperBound) {
		this.upperBound = upperBound;
	}

	public char getUpperBound() {
		return upperBound;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
