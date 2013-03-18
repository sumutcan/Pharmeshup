package com.querymanager;

public class SparqlQueryManager {

	private static SparqlQueryManager instance = null;
	
	private SparqlQueryManager()
	{
		
	}
	
	public static SparqlQueryManager getInstance()
	{
		if (instance == null)
			instance = new SparqlQueryManager();
		
		return instance;
	}
	
	public ISparqlQuery createQuery()
	{
		return new SparqlQuery();
	}
	
}
