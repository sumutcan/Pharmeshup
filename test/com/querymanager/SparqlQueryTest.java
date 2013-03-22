package com.querymanager;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.querymanager.elements.TriplePatternElement;

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
		q.addSelectParamaters(ISparqlQuery.NOT_DISTINCT,"?s", "?p", "?o");
		assertEquals("SELECT ?s ?p ?o \n"+
		"WHERE\n{\n}", q.buildQueryString());
	}

	@Test
	public void testAddFROM() {
		q = SparqlQueryManager.getInstance().createQuery();
		q.addFROM("http://asdasd.com");
		assertEquals("FROM <http://asdasd.com>\n", q.buildQueryString());
	}

	@Test
	public void testAddTriplePattern() {
		
		q = SparqlQueryManager.getInstance().createQuery();
		q.addSelectParamaters(ISparqlQuery.NOT_DISTINCT,"?s", "?p", "?o");
		q.addTriplePattern("?name", "?mbox","\"hebele\"" );
		q.addTriplePattern("?name", "owl:sameAs","?o" );
		assertEquals("SELECT ?s ?p ?o \n"+
				 "WHERE\n"+
				"{\n" +
				"\t?name ?mbox \"hebele\" .\n" +
				"\t?name owl:sameAs ?o .\n"+
				"}", q.buildQueryString());
	}

	@Test
	public void testAddGroupGraphPattern() {
		
		q = SparqlQueryManager.getInstance().createQuery();
		q.addSelectParamaters(ISparqlQuery.NOT_DISTINCT,"?s", "?p", "?o");
		q.addTriplePattern("?name", "?mbox","\"hebele\"" );
		q.addGroupGraphPattern("?name", "owl:sameAs","?o" );
		assertEquals("SELECT ?s ?p ?o \n"+
				 "WHERE\n"+
				"{\n" +
				"\t?name ?mbox \"hebele\" .\n" +
				"\t{?name owl:sameAs ?o .}\n"+
				"}", q.buildQueryString());
	}
	
	@Test 
	public void testAddConstruct()
	{
		q = SparqlQueryManager.getInstance().createQuery();
		q.addConstruct(new TriplePatternElement("?s", "?p", "?o"));
		assertEquals("CONSTRUCT\n{\n\t?s ?p ?o .\n}\n"+
		"WHERE\n{\n}", q.buildQueryString());
		
	}

	@Test @Ignore
	public void testAddOptionalPattern() {
		fail("Not yet implemented");
	}

	@Test @Ignore
	public void testBuildQueryString() {
		fail("Not yet implemented");
	}

}
