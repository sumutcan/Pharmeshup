package main.classes;

import java.util.ArrayList;

import main.classes.indexer.IndexUtil;

public class SearchTerm {

	
	private String searchString;
	private ArrayList<SearchResult> searchResults;
	
	public String getSearchString() {
		return searchString;
	}


	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}


	public SearchTerm ()
	{
		
		this.searchResults = new ArrayList<SearchResult>();
	}


	public ArrayList<SearchResult> search() throws Exception {
		
		searchResults = IndexUtil.getInstance().searchInIndexFile(getSearchString());
		
		return searchResults;
	}
	
	
	
}
