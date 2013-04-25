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

	@Override
	public void getData() throws Exception {
		
		try {

			ResultSet results = linkedDataAccess.getDBPediaData(drugName);
			
			while (results.hasNext())
			{
				QuerySolution row = results.next();
				setDescription(row.getLiteral("abstract").getString());
				setCasNumber(row.getLiteral("casNumber"));
				setWikiPage(new Link(getDrugName()+ " at Wikipedia", row.getResource("wikiPage").toString()));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("An error occured while retrieving DBPedia data: "+ e.getMessage());
		}
		
	}

	
}
