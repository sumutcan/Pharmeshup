package com.querymanager;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

import com.querymanager.elements.BaseElement;
import com.querymanager.elements.FromElement;
import com.querymanager.elements.PrefixElement;


class SparqlQuery implements ISparqlQuery {

	private String queryString = null;
	private ArrayList<PrefixElement> prefixes;
	private ArrayList<BaseElement> bases;
	private ArrayList<String> selectElements;
	private ArrayList<FromElement> fromElements;
	
	
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
		
		if (fromElements == null)
			fromElements = new ArrayList<FromElement>();
		
		fromElements.add(new FromElement(uri));
		
		return this;
		
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
			
			queryString += "SELECT ";
		
			for (String selectElement : selectElements)
			{
				queryString += selectElement+" ";
				
			}
			
			queryString += "\n";
			
		if (fromElements != null)
			for (FromElement fromElement : fromElements)
				queryString += fromElement;
				
		return queryString;
		
	}
	

}
