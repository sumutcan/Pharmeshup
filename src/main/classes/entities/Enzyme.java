package main.classes.entities;

import main.classes.dataaccess.ILinkedDataAccess;
import main.classes.utils.DataClearUtil;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

public class Enzyme {
	
	private String name;
	private String subject;
	private String molecularWeight;
	private String cellularLocation;
	private String reaction;
	private String specificFunction;
	private String theoreticalPi;
	
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
	
	public String getMolecularWeight() {
		return molecularWeight;
	}

	public void setMolecularWeight(Literal literal) {
		
		if(literal != null)
			this.molecularWeight = literal.getString();
	}

	public String getCellularLocation() {
		return cellularLocation;
	}

	public void setCellularLocation(Literal literal) {
		if (literal != null)
			this.cellularLocation = DataClearUtil.hexToString(literal.getString());
	}

	public String getReaction() {
		return reaction;
	}

	public void setReaction(Literal literal) {
		if(literal != null)
			this.reaction = DataClearUtil.hexToString(literal.getString());
	}

	public String getSpecificFunction() {
		return specificFunction;
	}

	public void setSpecificFunction(Literal literal) {
		if(literal != null)
			this.specificFunction = DataClearUtil.hexToString(literal.getString());
	}

	public String getTheoreticalPi() {
		return theoreticalPi;
	}

	public void setTheoreticalPi(Literal literal) {
		if(literal != null)
			this.theoreticalPi = DataClearUtil.hexToString(literal.getString());
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

	public void getEnzymeData(ILinkedDataAccess linkedDataAccess) throws Exception {
		
		linkedDataAccess.getEnzymeData(this);
		
	}
	
}
