package com.querymanager;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class SparqlQueryTest {

	ISparqlQuery q;
//	@BeforeClass
//	public void testSetup()
//	{
//		
//	}
	
	@Test
	public void testSparqlQuery() {
		q = SparqlQueryManager.getInstance().createQuery();
		assertEquals("", q.buildQueryString());
	}

	@Test
	public void testAddPrefix() {
		q = SparqlQueryManager.getInstance().createQuery();
		q.addPrefix("owl", "http://www.w3.org/2002/07/owl#");
		assertEquals("PREFIX owl: <http://www.w3.org/2002/07/owl#>\n", q.buildQueryString());
	}

	@Test
	public void testAddBase() {
		q = SparqlQueryManager.getInstance().createQuery();
		q.addBase("http://base.org");
		assertEquals("BASE <http://base.org>\n", q.buildQueryString());
	}

	@Test
	public void testAddSelectParamaters() {
		q = SparqlQueryManager.getInstance().createQuery();
		q.addSelectParamaters("?s", "?p", "?o");
		assertEquals("SELECT ?s ?p ?o \n", q.buildQueryString());
	}

	@Test
	public void testAddFROM() {
		q = SparqlQueryManager.getInstance().createQuery();
		q.addFROM("http://asdasd.com");
		assertEquals("FROM <http://asdasd.com>\n"+
					 "WHERE\n{\n}", q.buildQueryString());
	}

	@Test
	public void testAddTriplePattern() {
		
		q = SparqlQueryManager.getInstance().createQuery();
		q.addFROM("http://asdasd.com");
		q.addTriplePattern("?name", "?mbox","\"hebele\"" );
		assertEquals("FROM <http://asdasd.com>\n"+
				 "WHERE\n"+
				"{\n" +
				"\t?name ?mbox \"hebele\" .\n" +
				"}", q.buildQueryString());
	}

	@Test
	public void testAddGraphPattern() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddOptionalPattern() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuildQueryString() {
		fail("Not yet implemented");
	}

}
