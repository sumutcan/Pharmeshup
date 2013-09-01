package main.classes.datasets;

import main.classes.dataaccess.ILinkedDataAccess;
import main.classes.entities.Link;
import main.classes.entities.SearchResult;


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
		// TODO Auto-generated method stub
		
	}


	
}
