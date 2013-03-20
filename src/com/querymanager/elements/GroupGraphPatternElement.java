package com.querymanager.elements;

public class GroupGraphPatternElement extends TriplePattern {

	public GroupGraphPatternElement(String s, String p, String o) {
		super(s, p, o);
		// TODO Auto-generated constructor stub
	}
	
	public String toString()
	{
		return "\t{"+super.trippleString()+"}\n";
	}
}
