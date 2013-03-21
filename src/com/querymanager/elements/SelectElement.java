package com.querymanager.elements;

public class SelectElement {
	
	private String[] args;
	private boolean distinct;
	
	public SelectElement(String[] args, boolean distinct) {
		
		this.args = args;
		this.distinct = distinct;
		
	}
	
	public String toString()
	{
		String selectString = "SELECT ";
		
		if (distinct)
			selectString += "DISTINCT ";
		
		for (String arg : args)
			selectString += arg + " ";
		
		selectString += "\n";
		
		return selectString;
		
	}
	
}
