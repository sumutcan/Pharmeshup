package main.classes.entities;

import java.net.URI;
import java.net.URISyntaxException;

import com.hp.hpl.jena.rdf.model.Resource;

public class Link {
	
	private String name;
	private URI url;
	
	
	public Link(String url) throws Exception {
		
		try {
			this.url = new URI(url);
			this.name = this.url.getHost();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new Exception(url +" is not a valid URI");
		}
	}


	public String toString()
	{
		return name;
	}


	public URI getURL() throws Exception {
		return url;
	}
}
