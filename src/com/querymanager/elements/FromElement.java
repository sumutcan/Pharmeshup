package com.querymanager.elements;

public class FromElement {

	private String uri;
	
	public FromElement (String uri)
	{
		this.uri = uri; 
	}
	
	public String toString()
	{
		return "FROM <"+uri+">\n";
	}
}
