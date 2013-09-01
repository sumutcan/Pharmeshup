package main.classes;

import java.util.ArrayList;

import main.classes.dataaccess.LinkedDataAccessFactory;
import main.classes.datasets.DrugData;
import main.classes.entities.Enzyme;
import main.classes.entities.SearchResult;
import main.classes.indexer.SearchTerm;
import main.classes.threads.ThreadHandler;


public class DrugSearchController {

	
	public DrugSearchController() {
		

	}

	public ArrayList<SearchResult> searchDrug(String searchString) throws Exception
	{
		ThreadHandler.getInstance().pingEndpoints();
		SearchTerm searchTerm = new SearchTerm();
		
		searchTerm.setSearchString(searchString);
		
		return searchTerm.search();
	}

	public DrugData getDrugData(SearchResult selectedValue) throws Exception {
		
		DrugData drugData = new DrugData(selectedValue); 
		try {
			drugData.retrieve();
			drugData.construct();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Error occured while retrieving drug data: " + e.getMessage());
		}
		return drugData;
	}
	public void getEnzymeData(Enzyme selectedEnzyme) throws Exception
	{
		try {
			selectedEnzyme.getEnzymeData(LinkedDataAccessFactory.createLinkedDataAccess());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Error occured while retrieving enzyme data: " + e.getMessage());
		}
	}
}
