package com.querymanager.elements;

public class OptionalGraphPattern extends GroupGraphPatternElement {

	public OptionalGraphPattern(TriplePatternElement... triplePatterns) {
		super(triplePatterns);
		// TODO Auto-generated constructor stub
	}
	

	public String toString()
	{
		return "\tOPTIONAL " + groupPatternToString() + "\n";
	}
}
