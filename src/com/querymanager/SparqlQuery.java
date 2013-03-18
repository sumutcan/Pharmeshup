package com.querymanager;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

import com.querymanager.elements.BaseElement;
import com.querymanager.elements.PrefixElement;


class SparqlQuery implements ISparqlQuery {

	private String queryString = null;
	private ArrayList<PrefixElement> prefixes;
	private ArrayList<BaseElement> bases;
	private ArrayList<String> selectElements;
	
	
	SparqlQuery()
	{
		queryString = "";
	}


	@Override
	public ISparqlQuery addPrefix(String prefix, String uri) {
		// TODO Auto-generated method stub
		
		if (prefixes == null)
			prefixes = new ArrayList<PrefixElement>();
		
		prefixes.add(new PrefixElement(prefix, uri));
		
		return this;
	}


	@Override
	public ISparqlQuery addBase(String uri) {
		// TODO Auto-generated method stub
		if (bases == null)
			bases = new ArrayList<BaseElement>();
		
		bases.add(new BaseElement(uri));
		
		
		return this;
	}


	@Override
	public ISparqlQuery addSelectParamaters(String... args) {
		
		if (selectElements == null)
			selectElements = new ArrayList<String>();
		
		for (String arg : args)
			selectElements.add(arg);
		
		return this;
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


	@Override
	public String buildQueryString() {
		
		for (PrefixElement prefixElement : prefixes)
			queryString += prefixElement;
		
		if (bases != null)
			for (BaseElement baseElement : bases)
				queryString += baseElement;
		
		if (selectElements != null)
			for (String selectElement : selectElements)
			{
				queryString += "SELECT ";
				queryString += selectElement+" ";
				queryString += "\n";
			}
	
		
		return queryString;
		
	}
	

}
