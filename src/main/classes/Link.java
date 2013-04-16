package main.classes;

import java.net.URI;
import java.net.URISyntaxException;

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


	public URI getURL() throws Exception {
		// TODO Auto-generated method stub
		try {
			return new URI(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new Exception(url+" is not a valid URI");
		}
	}
}
