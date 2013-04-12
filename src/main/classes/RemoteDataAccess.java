package main.classes;

import java.util.ArrayList;


public class RemoteDataAccess implements ILinkedDataAccess {

	@Override
	public ArrayList<SearchResult> search(String searchTerm) throws Exception {

		
	return QueryCreator.getInstance().searchInIndexFile(searchTerm);

	}
}
