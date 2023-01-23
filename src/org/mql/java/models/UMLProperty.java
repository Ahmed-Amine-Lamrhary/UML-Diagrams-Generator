package org.mql.java.models;

import org.mql.java.enums.Visibility;

public abstract class UMLProperty extends UMLMember {
	protected Visibility visibility;
	protected String type;
	protected String simpleType;
	protected boolean _static;
	protected boolean _final;

	public UMLProperty(String name, Visibility visibility, String type, String simpleType, boolean _static, boolean _final) {
		super(name);
		this.visibility = visibility;
		this.type = type;
		this.simpleType = simpleType;
		this._static = _static;
		this._final = _final;
	}

	public Visibility getVisibility() {
		return visibility;
	}
	
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	
	public String getSimpleType() {
		return simpleType;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isStatic() {
		return _static;
	}
	
	public boolean isFinal() {
		return _final;
	}
}
