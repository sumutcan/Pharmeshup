package main.classes;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.tdb.solver.SolverLib;
import com.querymanager.ISparqlQuery;

public class DBPedia extends ADataSet {

	private Link wikiPage;
	private String description;
	private String drugName;
	
	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public DBPedia(ILinkedDataAccess linkedDataAccess) {
		
		super(linkedDataAccess);
		
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



	@Override
	public void getData() throws Exception {
		
		try {
			Query q = QueryFactory.create(SparqlQueryRepo.getInstance().getDBPediaDataQuery(drugName));
			ResultSet results = QueryCreator.getInstance().executeRemoteSelect("dbpedia", q);
			
			while (results.hasNext())
			{
				QuerySolution row = results.next();
				setDescription(row.getLiteral("abstract").getString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("An error occured while retrieving DBPedia data: "+ e.getMessage());
		}
		
	}

	
}
