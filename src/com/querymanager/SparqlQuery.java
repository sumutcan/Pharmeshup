package com.querymanager;

import java.util.Hashtable;

import com.querymanager.elements.PrefixElement;


class SparqlQuery implements ISparqlQuery {

	String queryString = null;
	Hashtable<String, PrefixElement> prefixes; 
	
	
	SparqlQuery()
	{
		queryString = "";
	}


	@Override
	public ISparqlQuery addPrefix(String prefix, String uri) {
		// TODO Auto-generated method stub
		
		if (prefixes == null)
			prefixes = new Hashtable<String, PrefixElement>();
		
		prefixes.put(prefix, new PrefixElement(prefix, uri));
		
		return this;
	}


	@Override
	public ISparqlQuery addBase(String prefix, String uri) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ISparqlQuery addSelectParamaters(String... args) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ISparqlQuery addFROM(String uri) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ISparqlQuery addTriplePattern(String s, String p, String o) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ISparqlQuery addGraphPattern(String s, String p, String o) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ISparqlQuery addOptionalPattern(String s, String p, String o) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
