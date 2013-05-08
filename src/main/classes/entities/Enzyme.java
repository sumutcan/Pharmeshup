package main.classes.entities;

import main.classes.dataaccess.ILinkedDataAccess;
import main.classes.utils.DataClearUtil;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

public class Enzyme {
	
	private String name;
	private String subject;
	
	public Enzyme(Resource resource, Literal name) {
		
		if (resource != null)
			this.subject = resource.toString();
		
		if (name != null)
			this.name = DataClearUtil.hexToString(name.getString());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String toString()
	{
		return name;
	}
	
	public boolean equals(Object o)
	{
		Enzyme e = (Enzyme)o;
		
		if (this.subject == e.getSubject())
			return true;
		
		return false;
		
	}

	public void getEnzymeData(ILinkedDataAccess createLinkedDataAccess) {
		// TODO Auto-generated method stub
		
	}
	
}
