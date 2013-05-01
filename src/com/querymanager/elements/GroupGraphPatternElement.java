package com.querymanager.elements;

import java.util.ArrayList;

public class GroupGraphPatternElement{
	
	
	private TriplePatternElement[] triplePatterns;
	
	public GroupGraphPatternElement(TriplePatternElement... triplePatterns) {
		
		this.triplePatterns = triplePatterns;
	}
	
	protected String groupPatternToString() {
		
		String temp = "{";
		for (TriplePatternElement t : triplePatterns)
			temp += t.trippleString();
		
		temp += "}";
		return temp;
	} 
	
	public String toString()
	{
		return "\t"+groupPatternToString()+"\n";
	}
}
