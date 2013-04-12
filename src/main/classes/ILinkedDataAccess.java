package main.classes;

import java.util.ArrayList;


public interface ILinkedDataAccess {

		public ArrayList<SearchResult> search(String searchTerm) throws Exception;
		

}
