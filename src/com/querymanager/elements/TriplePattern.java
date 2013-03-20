package com.querymanager.elements;

public class TriplePattern {

	private String subject;
	private String predicate;
	private String object;
	
	public TriplePattern(String s, String p, String o)
	{
		this.subject = s;
		this.predicate = p;
		this.object = o;
	}
	
	
	public String toString()
	{
		return "\t"+subject+" "+predicate+" "+object+" .\n";
	}

}
