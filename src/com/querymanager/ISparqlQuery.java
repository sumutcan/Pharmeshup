package com.querymanager;

import com.querymanager.elements.TriplePatternElement;

public interface ISparqlQuery {
	
	public static final boolean DISTINCT = true;
	public static final boolean NOT_DISTINCT = false;
	
	public ISparqlQuery addPrefix(String prefix, String uri);
	public ISparqlQuery addBase(String uri);
	public ISparqlQuery addSelectParamaters(boolean distinct, String... args);
	public ISparqlQuery addFROM(String uri);
	public ISparqlQuery addTriplePattern(String s, String p, String o);
	public ISparqlQuery addGroupGraphPattern(String s, String p, String o);
	public ISparqlQuery addConstruct(TriplePatternElement... args);
	public ISparqlQuery addOptionalPattern(String s, String p, String o);
	public String buildQueryString();
	
}
