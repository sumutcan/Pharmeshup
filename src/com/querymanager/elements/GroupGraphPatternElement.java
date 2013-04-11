package com.querymanager.elements;

public class GroupGraphPatternElement extends TriplePatternElement {
	
	UnionElement unionElement;
	
	public GroupGraphPatternElement(String s, String p, String o) {
		super(s, p, o);
	}
	
	public GroupGraphPatternElement(String s, String p, String o, UnionElement unionElement) {
		super(s, p, o);
		this.unionElement = unionElement;
	}
	
	public GroupGraphPatternElement(String s, String p, String o, UnionElement unionElement,
			FilterElement[] filters) {
		
		super(s, p, o, filters);
		this.unionElement = unionElement;
	}
	
	public GroupGraphPatternElement(String s, String p, String o,
			FilterElement[] filters) {
		
		super(s, p, o, filters);
		
	}

	protected String groupPatternToString() {
		
		
		if (unionElement != null)
			return "{"+super.trippleString()+"}" + " "+unionElement;
		
		return "{"+super.trippleString()+"}";
	} 
	
	public String toString()
	{
		return "\t"+groupPatternToString()+"\n";
	}
}
