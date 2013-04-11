package com.querymanager.elements;

import java.util.ArrayList;

public class TriplePatternElement {

	private String subject;
	private String predicate;
	private String object;
	private FilterElement[] filters;
	
	public TriplePatternElement(String s, String p, String o)
	{
		this.subject = s;
		this.predicate = p;
		this.object = o;
	}
	
	
	public TriplePatternElement(String s, String p, String o,
			FilterElement[] filters) {
		
		this.subject = s;
		this.predicate = p;
		this.object = o;
		this.filters = filters;
	}


	protected String trippleString()
	{
		if (filters != null)
		{
			String temp = subject+" "+predicate+" "+object+" .";
			
			for (FilterElement f : filters)
				temp += " FILTER "+f.getFilterString();
			
			return temp;
		}
		
		return subject+" "+predicate+" "+object+" .";
	}
	
	public String toString()
	{
		return "\t"+trippleString()+"\n";
	}

}
