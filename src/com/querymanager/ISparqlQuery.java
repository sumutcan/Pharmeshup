package com.querymanager;

public interface ISparqlQuery {
	
	public ISparqlQuery addPrefix(String prefix, String uri);
	public ISparqlQuery addBase(String uri);
	public ISparqlQuery addSelectParamaters(String... args);
	public ISparqlQuery addFROM(String uri);
	public ISparqlQuery addTriplePattern(String s, String p, String o);
	public ISparqlQuery addGroupGraphPattern(String s, String p, String o);
	public ISparqlQuery addOptionalPattern(String s, String p, String o);
	public String buildQueryString();
	
}
