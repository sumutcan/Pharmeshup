package com.querymanager.elements;

public class GroupGraphPatternElement extends TriplePatternElement {

	public GroupGraphPatternElement(String s, String p, String o) {
		super(s, p, o);
		// TODO Auto-generated constructor stub
	}
	
	protected String groupPatternToString() {
		
		return "{"+super.trippleString()+"}";
	} 
	
	public String toString()
	{
		return "\t"+groupPatternToString()+"\n";
	}
}
