package com.querymanager;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.querymanager.elements.FilterElement;
import com.querymanager.elements.SelectElement;
import com.querymanager.elements.TriplePatternElement;
import com.querymanager.elements.UnionElement;

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

	@Test 
	public void testAddOptionalPattern() throws Exception {
		q = SparqlQueryManager.getInstance().createQuery();
		q.addSelectParamaters(ISparqlQuery.NOT_DISTINCT,"?s", "?p", "?o");
		q.addTriplePattern("?name", "?mbox","\"hebele\"" );
		q.addOptionalPattern("?name", "owl:sameAs","?o" );
		assertEquals("SELECT ?s ?p ?o \n"+
				 "WHERE\n"+
				"{\n" +
				"\t?name ?mbox \"hebele\" .\n" +
				"\tOPTIONAL {?name owl:sameAs ?o .}\n"+
				"}", q.buildQueryString());
	}

	@Test
	public void testAddFilteredTriplePattern() {
		
		q = SparqlQueryManager.getInstance().createQuery();
		q.addSelectParamaters(ISparqlQuery.NOT_DISTINCT,"?s", "?p", "?o");
		q.addTriplePattern("?name", "?mbox","\"hebele\"" );
		q.addFiltredTriplePattern("?name", "owl:sameAs","?o", new FilterElement("regex(\"?s\",\"hebele\",i)"));
		assertEquals("SELECT ?s ?p ?o \n"+
				 "WHERE\n"+
				"{\n" +
				"\t?name ?mbox \"hebele\" .\n" +
				"\t?name owl:sameAs ?o . FILTER regex(\"?s\",\"hebele\",i)\n"+
				"}", q.buildQueryString());
	}

	@Test
	public void testAddFilteredGroupGraphPattern() {
		
		q = SparqlQueryManager.getInstance().createQuery();
		q.addSelectParamaters(ISparqlQuery.NOT_DISTINCT,"?s", "?p", "?o");
		q.addTriplePattern("?name", "?mbox","\"hebele\"" );
		q.addFilteredGroupGraphPattern("?name", "owl:sameAs","?o", new FilterElement("regex(\"?s\",\"hebele\",i)") );
		assertEquals("SELECT ?s ?p ?o \n"+
				 "WHERE\n"+
				"{\n" +
				"\t?name ?mbox \"hebele\" .\n" +
				"\t{?name owl:sameAs ?o . FILTER regex(\"?s\",\"hebele\",i)}\n"+
				"}", q.buildQueryString());
	}
	
	@Test 
	public void testAddFilteredOptionalPattern() throws Exception {
		q = SparqlQueryManager.getInstance().createQuery();
		q.addSelectParamaters(ISparqlQuery.NOT_DISTINCT,"?s", "?p", "?o");
		q.addTriplePattern("?name", "?mbox","\"hebele\"" );
		q.addFilteredOptionalPattern("?name", "owl:sameAs","?o", new FilterElement("regex(\"?s\",\"hebele\",i)") );
		assertEquals("SELECT ?s ?p ?o \n"+
				 "WHERE\n"+
				"{\n" +
				"\t?name ?mbox \"hebele\" .\n" +
				"\tOPTIONAL {?name owl:sameAs ?o . FILTER regex(\"?s\",\"hebele\",i)}\n"+
				"}", q.buildQueryString());
	}
	@Test
	public void testAddFROMNAMED() {
		q = SparqlQueryManager.getInstance().createQuery();
		q.addFROM("http://asdasd.com");
		q.addFROMNAMED("http://namedgraph.com");
		assertEquals("FROM <http://asdasd.com>\nFROM NAMED <http://namedgraph.com>\n", q.buildQueryString());
	}
	@Test
	public void testAddUNION()
	{
		q = SparqlQueryManager.getInstance().createQuery();
		q.addPrefix("dc10", "http://purl.org/dc/elements/1.0/");
		q.addPrefix("dc11", "http://purl.org/dc/elements/1.1/");
		q.addSelectParamaters(ISparqlQuery.NOT_DISTINCT, "?title");
		q.addGroupGraphPattern("?book", "dc10:title", "?title", new UnionElement("?book", "dc11:title", "?title"));
		
		
		String expected = "PREFIX dc10: <http://purl.org/dc/elements/1.0/>\n"+
						  "PREFIX dc11: <http://purl.org/dc/elements/1.1/>\n"+
						  "SELECT ?title \n"+
						  "WHERE\n" +
						  "{\n" +
						  "\t{?book dc10:title ?title .} UNION {?book dc11:title ?title .}\n" +					
						  "}";	
		assertEquals(expected, q.buildQueryString());
		
	}
	
	@Test
	
	public void testAddGRAPH () throws Exception
	{
		q = SparqlQueryManager.getInstance().createQuery();
		q.addPrefix("dc10", "http://purl.org/dc/elements/1.0/");
		q.addPrefix("dc11", "http://purl.org/dc/elements/1.1/");
		q.addSelectParamaters(ISparqlQuery.NOT_DISTINCT, "?title");
		q.addFROMNAMED("http://namedgraph.com");
		q.addGRAPH("?g", new TriplePatternElement("?s", "?p","?o"));
		
		String expected = "PREFIX dc10: <http://purl.org/dc/elements/1.0/>\n"+
						  "PREFIX dc11: <http://purl.org/dc/elements/1.1/>\n"+
						  "SELECT ?title \n"+
						  "FROM NAMED <http://namedgraph.com>\n"+
						  "WHERE\n" +
						  "{\n" +
						  "\tGRAPH ?g\n{\n"+
						  "\t?s ?p ?o .\n" +
						  "}\n" +
						  "}";	

		assertEquals(expected, q.buildQueryString());
	}
	
	@Test @Ignore
	public void testBuildQueryString() {
		fail("Not yet implemented");
	}

}
