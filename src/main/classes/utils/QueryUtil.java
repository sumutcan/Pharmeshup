package main.classes.utils;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Timer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.querymanager.ISparqlQuery;
import com.querymanager.SparqlQueryManager;
import com.querymanager.elements.FilterElement;
import com.querymanager.elements.UnionElement;

public class QueryUtil {

	private static QueryUtil instance = null;


	public static synchronized QueryUtil getInstance() {
		if (instance == null)
			instance = new QueryUtil();

		return instance;
	}

	private QueryUtil() {
	}

	
	public ResultSet executeRemoteSelect(String endpoint, Query q) throws Exception
	{
		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				Config.getInstance().getEndpoint(endpoint), q);
		
		return qexec.execSelect();

	}
	
	public ISparqlQuery getCommonPrefixes(ISparqlQuery q) throws Exception
	{
		Enumeration<String> keysEnum = Config.getInstance().getAllPrefixes().keys();
		
		while (keysEnum.hasMoreElements())
		{
			String prefix = keysEnum.nextElement();
			q.addPrefix(prefix, Config.getInstance().getAllPrefixes().get(prefix));
		}
		
		return q;
		
	}
	public boolean pingEndpoint(String endpointName)
	{
		try
		{
			
			return Config.getInstance().checkAvailableEndpoints(endpointName);
			
		}
		catch (Exception e)
		{
			return false;
		}
	}

}
