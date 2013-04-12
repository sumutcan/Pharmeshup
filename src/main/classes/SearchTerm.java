package main.classes;

import java.util.ArrayList;

public class SearchTerm {

	private ILinkedDataAccess linkedDataAccess;
	private String searchString;
	private ArrayList<SearchResult> searchResults;
	
	public String getSearchString() {
		return searchString;
	}


	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}


	public SearchTerm (ILinkedDataAccess linkedDataAccess)
	{
		this.linkedDataAccess = linkedDataAccess;
		this.searchResults = new ArrayList<SearchResult>();
	}


	public ArrayList<SearchResult> search() throws Exception {
		
		searchResults = linkedDataAccess.search(searchString);
		
		return searchResults;
	}
	
	
	
}
