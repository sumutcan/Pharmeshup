package main.classes;

import java.util.ArrayList;


public class DrugSearchController {

	ILinkedDataAccess linkedDataAccess; 
	
	public DrugSearchController(ILinkedDataAccess remoteDataAccess) {
		
		linkedDataAccess = remoteDataAccess;
	}

	public ArrayList<SearchResult> searchDrug(String searchTerm) throws Exception
	{
		ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();
		
		searchResults = linkedDataAccess.getData(searchTerm);
		
		return searchResults;
	}
}
