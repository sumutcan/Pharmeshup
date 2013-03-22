package com.querymanager.elements;

public class TriplePatternElement {

	private String subject;
	private String predicate;
	private String object;
	
	public TriplePatternElement(String s, String p, String o)
	{
		this.subject = s;
		this.predicate = p;
		this.object = o;
	}
	
	
	protected String trippleString()
	{
		return subject+" "+predicate+" "+object+" .";
	}
	
	public String toString()
	{
		return "\t"+trippleString()+"\n";
	}

}
