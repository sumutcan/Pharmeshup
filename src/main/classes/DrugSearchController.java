package main.classes;

import java.util.ArrayList;

import main.classes.entities.DrugData;
import main.classes.entities.SearchResult;
import main.classes.indexer.SearchTerm;
import main.classes.threads.ThreadHandler;


public class DrugSearchController {

	
	public DrugSearchController() {
		

	}

	public ArrayList<SearchResult> searchDrug(String searchString) throws Exception
	{
		ThreadHandler.getInstance().startThreads();
		SearchTerm searchTerm = new SearchTerm();
		
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
