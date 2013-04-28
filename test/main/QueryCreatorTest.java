package main;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import main.classes.Config;
import main.classes.QueryUtil;
import main.classes.SparqlQueryRepo;
import main.classes.indexer.IndexUtil;

import org.junit.Test;

import com.querymanager.ISparqlQuery;
import com.querymanager.SparqlQueryManager;

public class QueryCreatorTest {

	@Test
	public void testSearchInIndexFile() throws Exception {
		
		IndexUtil qc = IndexUtil.getInstance();
		assertEquals(2, qc.searchInIndexFile("naprox").size()); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//assertEquals(sdf.parse("12-04-2013"), Config.getInstance().getLastUpdateDate());
	
	}
	
	@Test
	public void testGetCommonPrefixes() throws Exception
	{
		QueryUtil qc = QueryUtil.getInstance();
		
		ISparqlQuery expected = SparqlQueryManager.getInstance().createQuery();
		expected.addPrefix("foaf", "http://xmlns.com/foaf/0.1/")
		.addPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
		.addPrefix("dbpedia-owl", "http://dbpedia.org/ontology/")
		.addPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#")
		.addPrefix("dbpprop", "http://dbpedia.org/property/");
		
		
		ISparqlQuery q = SparqlQueryManager.getInstance().createQuery();
		
		assertEquals(expected.buildQueryString(), qc.getCommonPrefixes(q).buildQueryString());
	}

	

}
