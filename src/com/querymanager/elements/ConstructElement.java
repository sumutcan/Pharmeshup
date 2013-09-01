package com.querymanager.elements;


public class ConstructElement {
	
	private TriplePatternElement[] triplePatterns;
	
	public ConstructElement(TriplePatternElement tripplePatterns[])
	{
		if (triplePatterns == null)
			this.triplePatterns = tripplePatterns;
		
	}
	
	public String toString()
	{
		String constructString = "CONSTRUCT\n{\n";
		for (TriplePatternElement tpe : triplePatterns)
			constructString += tpe;
			
		constructString +="}\n";
		
		return constructString;
	}
}
