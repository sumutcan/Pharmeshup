package main.classes;

public class Link {
	
	private String name;
	private String url;
	
	
	public String toString()
	{
		return "<a href=\""+url+"\">"+name+"</a>";
	}
}
