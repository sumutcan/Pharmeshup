package main.classes;

import java.util.ArrayList;


public class DrugSearchController {

	
	public DrugSearchController() {
		
		
	}

	public ArrayList<SearchResult> searchDrug(String searchString) throws Exception
	{
		SearchTerm searchTerm = new SearchTerm(LinkedDataAccessFactory.createLinkedDataAccess());
		
		searchTerm.setSearchString(searchString);
		
		return searchTerm.search();
	}

	public DrugData getDrugData(SearchResult selectedValue) throws Exception {
		
		DrugData drugData = new DrugData(selectedValue); 
		try {
			drugData.retrieve();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Error occured while retrieving drug data: " + e.getMessage());
		}
		return drugData;
	}
}
