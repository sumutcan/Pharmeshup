package com.querymanager;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

import com.querymanager.elements.BaseElement;
import com.querymanager.elements.FromElement;
import com.querymanager.elements.GroupGraphPatternElement;
import com.querymanager.elements.PrefixElement;
import com.querymanager.elements.SelectElement;
import com.querymanager.elements.TriplePattern;


class SparqlQuery implements ISparqlQuery {

	private String queryString = null;
	private ArrayList<PrefixElement> prefixes;
	private ArrayList<BaseElement> bases;
	private SelectElement selectElement;
	private ArrayList<FromElement> fromElements;
	private ArrayList<TriplePattern> tripplePatterns;

	 
	
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
	public ISparqlQuery addSelectParamaters(boolean distinct, String... args) {
		
			selectElement = new SelectElement(args,distinct);
		
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
		
		if (tripplePatterns == null)
			tripplePatterns = new ArrayList<TriplePattern>();
		
		tripplePatterns.add(new TriplePattern(s, p, o));
		
		return this;
	}


	@Override
	public ISparqlQuery addGroupGraphPattern(String s, String p, String o) {
		
		if (tripplePatterns == null)
			tripplePatterns = new ArrayList<TriplePattern>();
		
		tripplePatterns.add(new GroupGraphPatternElement(s, p, o));
		
		return this;
	}


	@Override
	public ISparqlQuery addOptionalPattern(String s, String p, String o) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String buildQueryString() {
		if (prefixes != null)
			for (PrefixElement prefixElement : prefixes)
				queryString += prefixElement;
		
		if (bases != null)
			for (BaseElement baseElement : bases)
				queryString += baseElement;
		
		if (selectElement != null)
			queryString += selectElement;
				
		
		if (fromElements != null)
		{
			for (FromElement fromElement : fromElements)
				queryString += fromElement;
		
		queryString += "WHERE\n{\n";
		
		if (tripplePatterns != null)
			for (TriplePattern triplePattern : tripplePatterns)
				queryString += triplePattern;
				
		
		queryString += "}";
		}
		return queryString;
		
	}
	

}
