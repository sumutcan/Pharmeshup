package main.classes.threads;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Timer;

import main.classes.utils.Config;



import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.sparql.engine.http.HttpQuery;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

public class PingEndpointsThread implements Runnable {
	
	private String endpoint = null;
	public PingEndpointsThread(String endpoint) {
	
		this.endpoint = endpoint;
	}

	@Override
	public void run() {
		
		
		String q = "ASK WHERE { ?s ?p ?o }";
		Query query = QueryFactory.create(q);
		
		QueryEngineHTTP qexec = (QueryEngineHTTP) QueryExecutionFactory.sparqlService(endpoint, query);
		qexec.execAsk();
		
		
	}

}
