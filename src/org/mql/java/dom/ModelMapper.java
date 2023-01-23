package org.mql.java.dom;

import java.util.List;
import java.util.Vector;

import org.mql.java.models.Project;
import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLClass;
import org.mql.java.models.UMLClassifier;
import org.mql.java.models.UMLConstant;
import org.mql.java.models.UMLEnum;
import org.mql.java.models.UMLMember;
import org.mql.java.models.UMLOperation;
import org.mql.java.models.UMLPackage;
import org.mql.java.models.UMLProperty;
import org.mql.java.models.UMLRelation;
import org.w3c.dom.Document;

public class ModelMapper {
	
	public XMLNode projectToXMLNode(Project project) {
		XMLNode projectNode = new XMLNode("project");
		projectNode.setAttribute("name", project.getName());
		
		if (!project.getPackages().isEmpty()) {
			XMLNode packagesNode = new XMLNode("packages", projectNode.getDocument());
			for (UMLPackage umlPackage : project.getPackages()) {
				packagesNode.appendChild(XMLNodeFromUMLPackage(umlPackage, projectNode.getDocument()));
			}
			projectNode.appendChild(packagesNode);
			
			XMLNode relationsNode = new XMLNode("relations", projectNode.getDocument());
			for (UMLRelation relation : project.getRelations()) {
				relationsNode.appendChild(XMLNodeFromUmlRelation(relation, projectNode.getDocument()));
			}
			projectNode.appendChild(relationsNode);
		}
		
		return projectNode;
	}
	
	private XMLNode XMLNodeFromUMLPackage(UMLPackage umlPackage, Document document) {
		XMLNode packageNode = new XMLNode("package", document);
		packageNode.setAttribute("name", umlPackage.getName());
		
		for (UMLClassifier umlClassifier : umlPackage.getClassifiers()) {
			XMLNode classifierNode = XMLNodeFromUmlClassifier(umlClassifier, document);
			packageNode.appendChild(classifierNode);
		}
		
		return packageNode;
	}
	
	private XMLNode XMLNodeFromUmlClassifier(UMLClassifier umlClassifier, Document document) {
		XMLNode classifierNode;
		
		if (umlClassifier instanceof UMLEnum) {
			classifierNode = new XMLNode("enumeration", document);
			classifierNode.setAttribute("name", umlClassifier.getName());
			classifierNode.setAttribute("simple-name", umlClassifier.getSimpleName());
			
			for (UMLMember member : umlClassifier.getUmlMembers()) {
				classifierNode.appendChild(XMLNodeFromUmlMember(member, document));
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
					attributesNode.appendChild(XMLNodeFromUmlMember(umlAttribute, document));
				}
				
				classifierNode.appendChild(attributesNode);
			}
			
			if (!operations.isEmpty()) {
				XMLNode operationsNode = new XMLNode("operations", document);
				for (UMLOperation umlOperation : operations) {
					operationsNode.appendChild(XMLNodeFromUmlMember(umlOperation, document));
				}
				classifierNode.appendChild(operationsNode);
			}
		}
		
		return classifierNode;
	}
	
	private XMLNode XMLNodeFromUmlMember(UMLMember umlMember, Document document) {
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
			if (umlProperty.getSimpleType() != null)
				memberNode.setAttribute("type", umlProperty.getSimpleType());
			memberNode.setAttribute("final", umlProperty.isFinal() + "");
			memberNode.setAttribute("static", umlProperty.isStatic() + "");
			
			if (umlProperty instanceof UMLOperation) {
				UMLOperation umlOperation = (UMLOperation) umlProperty;
				memberNode.setAttribute("constructor", umlOperation.isConstructor() + "");
				
				if (!umlOperation.getParameters().isEmpty()) {
					XMLNode parametersNode = new XMLNode("parameters", document);
					for (String parameter : umlOperation.getParameters()) {
						XMLNode parameterNode = new XMLNode("parameter", document);
						parameterNode.setAttribute("type", parameter);
						parametersNode.appendChild(parameterNode);
					}
					memberNode.appendChild(parametersNode);					
				}
			}
		}
		
		return memberNode;
	}
	
	private XMLNode XMLNodeFromUmlRelation(UMLRelation relation, Document document) {
		XMLNode relationNode = new XMLNode("relation", document);
		relationNode.setAttribute("child", relation.getChild().getName());
		relationNode.setAttribute("parent", relation.getParent().getName());
		relationNode.setAttribute("type", relation.getType().name());
		
		return relationNode;
	}
}
