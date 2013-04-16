package main.classes;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.tdb.solver.SolverLib;
import com.querymanager.ISparqlQuery;

public class DBPedia implements ADataSet {

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
			Query q = QueryFactory.create(SparqlQueryRepo.getInstance().getDBPediaDataQuery(drugName));
			ResultSet results = QueryCreator.getInstance().executeRemoteSelect("dbpedia", q);
			
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
