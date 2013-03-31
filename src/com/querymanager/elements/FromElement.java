package com.querymanager.elements;

public class FromElement {

	protected String uri;
	
	public FromElement (String uri)
	{
		this.uri = uri; 
	}
	
	public String toString()
	{
		return "FROM <"+uri+">\n";
	}
}
