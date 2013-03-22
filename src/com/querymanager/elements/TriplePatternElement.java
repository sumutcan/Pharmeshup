package com.querymanager.elements;

public class TriplePatternElement {

	private String subject;
	private String predicate;
	private String object;
	private FilterElement filter;
	
	public TriplePatternElement(String s, String p, String o)
	{
		this.subject = s;
		this.predicate = p;
		this.object = o;
	}
	
	
	public TriplePatternElement(String s, String p, String o,
			FilterElement filter) {
		
		this.subject = s;
		this.predicate = p;
		this.object = o;
		this.filter = filter;
	}


	protected String trippleString()
	{
		if (filter != null)
			return subject+" "+predicate+" "+object+" ."+" FILTER "+filter.getFilterString();
		
		return subject+" "+predicate+" "+object+" .";
	}
	
	public String toString()
	{
		return "\t"+trippleString()+"\n";
	}

}
