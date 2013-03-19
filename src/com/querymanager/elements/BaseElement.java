package com.querymanager.elements;

public class BaseElement {

	
	String baseUri;
	
	public BaseElement(String baseUri) {
		// TODO Auto-generated constructor stub
	
		this.baseUri = baseUri;
	}

	public String toString()
	{
		return "BASE <"+baseUri+">\n";
	}
	
}
