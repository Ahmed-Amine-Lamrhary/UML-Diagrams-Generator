package org.mql.java.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.mql.java.enums.Visibility;
import org.mql.java.models.Project;
import org.mql.java.models.UMLAttribute;
import org.mql.java.models.UMLClass;
import org.mql.java.models.UMLClassifier;
import org.mql.java.models.UMLConstant;
import org.mql.java.models.UMLEnum;
import org.mql.java.models.UMLInterface;
import org.mql.java.models.UMLOperation;
import org.mql.java.models.UMLPackage;
import org.mql.java.parsers.Parser;
import org.w3c.dom.Document;

public class ProjectDOMParser implements Parser {	
	private Document document;
	private Project project;
	
	public ProjectDOMParser() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Project getProject() {
		return project;
	}
	
	public void persist() {
		ModelMapper mapper = new ModelMapper(document);
		XMLNode projectNode = mapper.xmlNodeFromModel(Project.getInstance());
		
		try {
			String path = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()
					+ "\\project-dom.xml";
			
			projectNode.persist(path);
		} catch (Exception e) {
			
		}
	}

	@Override
	public void parse(File file) throws Exception {
		XMLNode projectNode = new XMLNode(file);
		
		project = Project.getInstance();
		project.setName(projectNode.getAttribute("name"));
		
		for (XMLNode packageNode : projectNode.getChild("packages").getChildren()) {
			UMLPackage umlPackage = new UMLPackage(packageNode.getAttribute("name"));
			
			for (XMLNode classifierNode : packageNode.getChildren()) {
				UMLClassifier umlClassifier;
				String name = classifierNode.getAttribute("name");
				String simpleName = classifierNode.getAttribute("simple-name");
				
				if (classifierNode.getName().equals("enumeration")) {
					umlClassifier = new UMLEnum(name, simpleName);
					
					for (XMLNode constantNode : classifierNode.getChildren()) {
						umlClassifier.addUMLMember(new UMLConstant(constantNode.getAttribute("value")));
					}
				}
				else {
					if (classifierNode.getName().equals("interface")) {
						umlClassifier = new UMLInterface(name, simpleName);						
					}
					else {
						boolean isAbstract = classifierNode.getBooleanAttribute("abstract");
						umlClassifier = new UMLClass(name, simpleName, isAbstract);
					}
					
					if (classifierNode.getChild("attributes") != null) {
						for (XMLNode attributeNode : classifierNode.getChild("attributes").getChildren()) {
							String attributeName = attributeNode.getAttribute("name");
							boolean isAttributeStatic = attributeNode.getBooleanAttribute("static");
							boolean isAttributeFinal = attributeNode.getBooleanAttribute("final");
							String attributeType = attributeNode.getAttribute("type");
							String attributeSimpleType = attributeNode.getAttribute("simple-type");
							String attributeVisibility = attributeNode.getAttribute("visibility");
							
							umlClassifier.addUMLMember(new UMLAttribute(attributeName, Visibility.valueOf(attributeVisibility), attributeType, attributeSimpleType, isAttributeStatic, isAttributeFinal));
						}
					}
					
					if (classifierNode.getChild("operations") != null) {
						for (XMLNode operationNode : classifierNode.getChild("operations").getChildren()) {
							String operationName = operationNode.getAttribute("name");
							boolean isOperationConstructor = operationNode.getBooleanAttribute("constructor");
							boolean isOperationStatic = operationNode.getBooleanAttribute("static");
							boolean isOperationFinal = operationNode.getBooleanAttribute("final");
							String operationType = operationNode.getAttribute("type");
							String operationSimpleType = operationNode.getAttribute("simple-type");
							String operationVisibility = operationNode.getAttribute("visibility");
							
							UMLOperation umlOperation = new UMLOperation(operationName, Visibility.valueOf(operationVisibility), operationType, operationSimpleType, isOperationStatic, isOperationFinal, isOperationConstructor);
							
							if (operationNode.getChild("parameters") != null) {
								for (XMLNode parameterNode : operationNode.getChild("parameters").getChildren()) {
									umlOperation.addParameter(parameterNode.getAttribute("type"));
								}								
							}
							
							umlClassifier.addUMLMember(umlOperation);
						}
					}
				}
				
				umlPackage.addClassifier(umlClassifier);
			}
			
			project.addPackage(umlPackage);
		}
	}

}
