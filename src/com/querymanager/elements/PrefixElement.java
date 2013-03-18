package com.querymanager.elements;

public class PrefixElement {

	String prefix;
	String uri;
	
	public PrefixElement(String prefix, String uri) {
		// TODO Auto-generated constructor stub
		
		this.prefix = prefix;
		this.uri = uri;
	}

	public String toString()
	{
		return "PREFIX "+prefix+": <"+uri+">\n";
	}
	
}
