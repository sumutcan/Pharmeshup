package com.querymanager.elements;

public class OptionalGraphPattern extends GroupGraphPatternElement {

	public OptionalGraphPattern(String s, String p, String o) {
		super(s, p, o);
		// TODO Auto-generated constructor stub
	}
	
	public OptionalGraphPattern(String s, String p, String o,
			FilterElement[] filters) {
		
		super(s, p, o, filters);
	}

	public String toString()
	{
		return "\tOPTIONAL " + groupPatternToString() + "\n";
	}
}
