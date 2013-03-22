package com.querymanager.elements;

public class GroupGraphPatternElement extends TriplePatternElement {

	public GroupGraphPatternElement(String s, String p, String o) {
		super(s, p, o);

	}
	
	public GroupGraphPatternElement(String s, String p, String o,
			FilterElement filter) {
		
		super(s, p, o, filter);
	}

	protected String groupPatternToString() {
		
		return "{"+super.trippleString()+"}";
	} 
	
	public String toString()
	{
		return "\t"+groupPatternToString()+"\n";
	}
}
