package org.mql.java.dom;

import java.util.List;
import java.util.Vector;

import javax.xml.XMLConstants;

import org.mql.java.models.Project;
import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLClass;
import org.mql.java.models.UMLClassifier;
import org.mql.java.models.UMLConstant;
import org.mql.java.models.UMLEnum;
import org.mql.java.models.UMLMember;
import org.mql.java.models.UMLOperation;
import org.mql.java.models.UMLPackage;
import org.mql.java.models.UMLParameter;
import org.mql.java.models.UMLProperty;
import org.mql.java.models.UMLRelation;
import org.w3c.dom.Document;

public class ModelMapper {
	private Document document;
	
	public ModelMapper(Document document) {
		this.document = document;
	}
	
	public XMLNode xmlNodeFromModel(Object model) {
		if (model instanceof Project) {
			Project project = (Project) model;
			XMLNode projectNode = new XMLNode("project", document);
			projectNode.setAttribute("name", project.getName());
			projectNode.setAttribute("xsi:noNamespaceSchemaLocation", "../resources/schema.xsd");
			projectNode.setAttribute("xmlns:xsi", XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
			
			if (!project.getPackages().isEmpty()) {
				XMLNode packagesNode = new XMLNode("packages", document);
				for (UMLPackage umlPackage : project.getPackages()) {
					packagesNode.appendChild(xmlNodeFromModel(umlPackage));
				}
				projectNode.appendChild(packagesNode);
				
				XMLNode relationsNode = new XMLNode("relations", document);
				for (UMLRelation relation : project.getRelations()) {
					relationsNode.appendChild(xmlNodeFromModel(relation));
				}
				projectNode.appendChild(relationsNode);
			}
			
			return projectNode;
		}
		else if (model instanceof UMLPackage) {
			UMLPackage umlPackage = (UMLPackage) model;
			XMLNode packageNode = new XMLNode("package", document);
			packageNode.setAttribute("name", umlPackage.getName());
			
			for (UMLClassifier umlClassifier : umlPackage.getClassifiers()) {
				XMLNode classifierNode = xmlNodeFromModel(umlClassifier);
				packageNode.appendChild(classifierNode);
			}
			
			return packageNode;
		}
		else if (model instanceof UMLClassifier) {
			UMLClassifier umlClassifier = (UMLClassifier) model;
			XMLNode classifierNode;
			
			if (umlClassifier instanceof UMLEnum) {
				classifierNode = new XMLNode("enumeration", document);
				classifierNode.setAttribute("name", umlClassifier.getName());
				classifierNode.setAttribute("simple-name", umlClassifier.getSimpleName());
				
				for (UMLMember member : umlClassifier.getUmlMembers()) {
					classifierNode.appendChild(xmlNodeFromModel(member));
				}
			}
			else {
				if (umlClassifier instanceof UMLClass) {
					classifierNode = new XMLNode("class", document);
					classifierNode.setAttribute("abstract", ((UMLClass) umlClassifier).isAbstract() + "");
					classifierNode.setAttribute("name", umlClassifier.getName());
					classifierNode.setAttribute("simple-name", umlClassifier.getSimpleName());
				}
				else {
					classifierNode = new XMLNode("interface", document);
					classifierNode.setAttribute("name", umlClassifier.getName());
					classifierNode.setAttribute("simple-name", umlClassifier.getSimpleName());
				}
				
				List<UMLAttribute> attributes = new Vector<>();
				List<UMLOperation> operations = new Vector<>();
				
				for (UMLMember umlMember : umlClassifier.getUmlMembers()) {
					if (umlMember instanceof UMLAttribute) {
						attributes.add((UMLAttribute) umlMember);
					}
					else {
						operations.add((UMLOperation) umlMember);
					}
				}
				
				if (!attributes.isEmpty()) {
					XMLNode attributesNode = new XMLNode("attributes", document);
					
					for (UMLAttribute umlAttribute : attributes) {
						attributesNode.appendChild(xmlNodeFromModel(umlAttribute));
					}
					
					classifierNode.appendChild(attributesNode);
				}
				
				if (!operations.isEmpty()) {
					XMLNode operationsNode = new XMLNode("operations", document);
					for (UMLOperation umlOperation : operations) {
						operationsNode.appendChild(xmlNodeFromModel(umlOperation));
					}
					classifierNode.appendChild(operationsNode);
				}
			}
			
			return classifierNode;
		}
		else if (model instanceof UMLMember) {
			UMLMember umlMember = (UMLMember) model;
			XMLNode memberNode;
			
			if (umlMember instanceof UMLConstant) {
				memberNode = new XMLNode("constant", document);
				memberNode.setAttribute("value", umlMember.getName());
			}
			else {
				UMLProperty umlProperty = (UMLProperty) umlMember;
				
				if (umlProperty instanceof UMLAttribute) {
					memberNode = new XMLNode("attribute", document);				
				}
				else {
					memberNode = new XMLNode("operation", document);
				}
				
				memberNode.setAttribute("name", umlMember.getName());
				memberNode.setAttribute("visibility", umlProperty.getVisibility().name());
				if (umlProperty.getType() != null && umlProperty.getSimpleType() != null) {
					memberNode.setAttribute("type", umlProperty.getType());
					memberNode.setAttribute("simple-type", umlProperty.getSimpleType());
				}
				memberNode.setAttribute("final", umlProperty.isFinal() + "");
				memberNode.setAttribute("static", umlProperty.isStatic() + "");
				
				if (umlProperty instanceof UMLOperation) {
					UMLOperation umlOperation = (UMLOperation) umlProperty;
					memberNode.setAttribute("constructor", umlOperation.isConstructor() + "");
					
					if (!umlOperation.getParameters().isEmpty()) {
						XMLNode parametersNode = new XMLNode("parameters", document);
						for (UMLParameter parameter : umlOperation.getParameters()) {
							XMLNode parameterNode = new XMLNode("parameter", document);
							parameterNode.setAttribute("type", parameter.getSimpleType());
							parametersNode.appendChild(parameterNode);
						}
						memberNode.appendChild(parametersNode);					
					}
				}
			}
			
			return memberNode;
		}
		else if (model instanceof UMLRelation) {
			UMLRelation umlRelation = (UMLRelation) model;
			XMLNode relationNode = new XMLNode("relation", document);
			relationNode.setAttribute("child", umlRelation.getChild().getName());
			relationNode.setAttribute("parent", umlRelation.getParent().getName());
			relationNode.setAttribute("type", umlRelation.getType().name());
			
			return relationNode;
		}
		
		return null;
	}

}
