package com.querymanager;

import com.querymanager.elements.FilterElement;
import com.querymanager.elements.TriplePatternElement;

public interface ISparqlQuery {
	
	public static final boolean DISTINCT = true;
	public static final boolean NOT_DISTINCT = false;
	public static final int ORDER_BY_ASC = 0;
	public static final int ORDER_BY_DESC = 1;
	
	public ISparqlQuery addPrefix(String prefix, String uri);
	public ISparqlQuery addBase(String uri);
	public ISparqlQuery addSelectParamaters(boolean distinct, String... args);
	public ISparqlQuery addFROM(String uri);
	public ISparqlQuery addTriplePattern(String s, String p, String o);
	public ISparqlQuery addFiltredTriplePattern(String s, String p, String o, FilterElement filter);
	public ISparqlQuery addGroupGraphPattern(String s, String p, String o);
	public ISparqlQuery addFilteredGroupGraphPattern(String s, String p, String o, FilterElement filter);
	public ISparqlQuery addConstruct(TriplePatternElement... args);
	public ISparqlQuery addOptionalPattern(String s, String p, String o) throws Exception;
	public ISparqlQuery addFilteredOptionalPattern(String s, String p, String o, FilterElement filter) throws Exception;
	public String buildQueryString();
	
}
