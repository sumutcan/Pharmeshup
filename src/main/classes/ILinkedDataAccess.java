package main.classes;

import java.util.ArrayList;


public interface ILinkedDataAccess {

		public ArrayList<SearchResult> getData(String searchTerm) throws Exception;
		

}
