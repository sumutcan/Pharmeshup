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
}
