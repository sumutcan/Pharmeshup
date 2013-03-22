package com.querymanager;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

import com.querymanager.elements.BaseElement;
import com.querymanager.elements.ConstructElement;
import com.querymanager.elements.FilterElement;
import com.querymanager.elements.FromElement;
import com.querymanager.elements.GroupGraphPatternElement;
import com.querymanager.elements.OptionalGraphPattern;
import com.querymanager.elements.PrefixElement;
import com.querymanager.elements.SelectElement;
import com.querymanager.elements.TriplePatternElement;


class SparqlQuery implements ISparqlQuery {

	private String queryString = null;
	private ArrayList<PrefixElement> prefixes;
	private ArrayList<BaseElement> bases;
	private SelectElement selectElement;
	private ArrayList<FromElement> fromElements;
	private ArrayList<TriplePatternElement> tripplePatterns;
	private ConstructElement constructElement;

	 
	
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
			tripplePatterns = new ArrayList<TriplePatternElement>();
		
		tripplePatterns.add(new TriplePatternElement(s, p, o));
		
		return this;
	}


	@Override
	public ISparqlQuery addGroupGraphPattern(String s, String p, String o) {
		
		if (tripplePatterns == null)
			tripplePatterns = new ArrayList<TriplePatternElement>();
		
		tripplePatterns.add(new GroupGraphPatternElement(s, p, o));
		
		return this;
	}


	@Override
	public ISparqlQuery addOptionalPattern(String s, String p, String o) throws Exception {
		
		if(tripplePatterns == null)
			throw new Exception("WHERE clause must have at least one non-optional pattern.");
		
		tripplePatterns.add(new OptionalGraphPattern(s, p, o));
		
		return null;
	}

	@Override
	public ISparqlQuery addConstruct(TriplePatternElement... args) {
		
		constructElement = new ConstructElement(args);


		return this;
	}


	@Override
	public ISparqlQuery addFiltredTriplePattern(String s, String p, String o,
			FilterElement filter) {
		
		if (tripplePatterns == null)
			tripplePatterns = new ArrayList<TriplePatternElement>();
		
		tripplePatterns.add(new TriplePatternElement(s, p, o, filter));
		
		
		return this;
	}


	@Override
	public ISparqlQuery addFilteredGroupGraphPattern(String s, String p,
			String o, FilterElement filter) {
		
		if (tripplePatterns == null)
			tripplePatterns = new ArrayList<TriplePatternElement>();
		
		tripplePatterns.add(new GroupGraphPatternElement(s, p, o, filter));
		
		return this;
		
	}


	@Override
	public ISparqlQuery addFilteredOptionalPattern(String s, String p,
			String o, FilterElement filter) throws Exception {
		
		if(tripplePatterns == null)
			throw new Exception("WHERE clause must have at least one non-optional pattern.");
		
		tripplePatterns.add(new OptionalGraphPattern(s, p, o, filter));
		
		return this;
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
		else if (constructElement != null)
			queryString += constructElement;
			
	
		if (fromElements != null)
			for (FromElement fromElement : fromElements)
				queryString += fromElement;
		
		if (selectElement != null || constructElement != null) {
			queryString += "WHERE\n{\n";

			if (tripplePatterns != null)
				for (TriplePatternElement triplePattern : tripplePatterns)
					queryString += triplePattern;
			
			queryString += "}";
		}
		return queryString;
		
	}




	

}
