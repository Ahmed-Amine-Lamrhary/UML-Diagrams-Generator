package org.mql.java.models;

import org.mql.java.enums.Visibility;

public class UMLAttribute extends UMLProperty {
	private Multiplicity multiplicity;
	
	public UMLAttribute(String name, Visibility visibility, String type, boolean _static, boolean _final) {
		super(name, visibility, type, _static, _final);
	}

	public void setMultiplicity(Multiplicity multiplicity) {
		if (multiplicity != null)
			this.multiplicity = multiplicity;
	}
	
	public boolean isMultiple() {
		return multiplicity.getUpperBound() == 'n';
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
