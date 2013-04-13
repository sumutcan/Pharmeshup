package main.classes;

import com.hp.hpl.jena.rdf.model.Resource;

public class Link {
	
	private String name;
	private String url;
	
	
	public Link(String name, String url) {
		
		this.name = name;
		this.url = url;
	}


	public String toString()
	{
		return name;
	}
}
