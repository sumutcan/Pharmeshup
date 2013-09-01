package main.classes.datasets;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Model;

import main.classes.dataaccess.ILinkedDataAccess;
import main.classes.dataaccess.remote.RemoteDataAccess;
import main.classes.dataaccess.remote.SparqlQueryRepo;
import main.classes.entities.Link;
import main.classes.entities.SearchResult;
import main.classes.utils.QueryUtil;


public class DBPedia extends ADataSet {

	private Link wikiPage;
	private String description;
	
	public DBPedia(ILinkedDataAccess linkedDataAccess,
			SearchResult selectedValue) {
		
		super (linkedDataAccess, selectedValue);
		this.setDrugbankID(selectedValue.getDrugbankID());
		this.setDrugName(selectedValue.getDrugName());
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

			getLinkedDataAccess().getDBPediaData(this);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("An error occured while retrieving DBPedia data: "+ e.getMessage());
		}
		
	}

	@Override
	public void constructData() throws Exception {
		
		RemoteDataAccess constructAccess = (RemoteDataAccess) getLinkedDataAccess();
		constructAccess.constructDBPediaData(this);

		
	}


	
}
