package org.mql.java.dom;

import java.io.File;

import org.mql.java.models.Project;
import org.mql.java.parsers.Parser;

public class ProjectDOMParser implements Parser {	
	public ProjectDOMParser() {
		persist();
	}
	
	private void persist() {
		ModelMapper mapper = new ModelMapper();
		XMLNode projectNode = mapper.projectToXMLNode(Project.getInstance());
		
		try {
			projectNode.persist();
		} catch (Exception e) {
			
		}
	}

	@Override
	public void parse(File file) throws Exception {
		// TODO : read xml file
	}

}
