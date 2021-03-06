package main.classes.utils;


import java.util.Enumeration;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ReadWrite;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.querymanager.ISparqlQuery;


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
	public synchronized boolean pingEndpoint(String endpointName)
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
	public synchronized Model executeRemoteConstruct(Query q, String endpoint) throws Exception
	{
		QueryExecution qexec = QueryExecutionFactory.sparqlService(Config.getInstance().getEndpoint(endpoint), q);
		return qexec.execConstruct();
	}
	
	public synchronized void storeModel(Model drugGraph, String graphName)
	{
		Dataset ds = TDBFactory.createDataset(System.getProperty("user.dir")+"/data");
		
		ds.begin(ReadWrite.WRITE);
		Model m = ds.getNamedModel(graphName);

		if (!m.isIsomorphicWith(drugGraph)) {
			m.add(drugGraph);

			m.commit();
			ds.commit();

			m.close();
			ds.close();
		}
	}
	
	
}
