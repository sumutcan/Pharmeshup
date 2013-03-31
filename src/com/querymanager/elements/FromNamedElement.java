package com.querymanager.elements;

public class FromNamedElement {

	String uri;
	
	public FromNamedElement(String uri) {
		
		this.uri = uri;
	}
	
	public String toString()
	{
		return"FROM NAMED <"+uri+">\n";
		
	}

}
