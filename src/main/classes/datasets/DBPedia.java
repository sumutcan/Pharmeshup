package main.classes.datasets;

import main.classes.Link;
import main.classes.dataaccess.ILinkedDataAccess;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;

public class DBPedia implements IDataSet {

	private Link wikiPage;
	private String description;
	private String drugName;
	private String casNumber;
	private String drugbankID;
	private ILinkedDataAccess linkedDataAccess;
	
	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public DBPedia(ILinkedDataAccess linkedDataAccess) {
		
		this.linkedDataAccess = linkedDataAccess;
		
	}
	
	public Link getWikiPage() {
		return wikiPage;
	}
	public void setWikiPage(Link wikiPage) {
		this.wikiPage = wikiPage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCasNumber() {
		return casNumber;
	}
	public void setCasNumber(Literal literal) {
		
		if (literal == null)
			this.casNumber = null;
		else
			this.casNumber = literal.getString();
	}

	public String getDrugbankID() {
		return drugbankID;
	}




	@Override
	public void getData() throws Exception {
		
		try {

			linkedDataAccess.getDBPediaData(this);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("An error occured while retrieving DBPedia data: "+ e.getMessage());
		}
		
	}

	public void setDrugbankID(String drugbankID) {
		
		if (this.drugbankID == null)
			this.drugbankID = drugbankID;
		
		
	}

	
}
