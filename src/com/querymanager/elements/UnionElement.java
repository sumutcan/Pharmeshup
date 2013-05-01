package com.querymanager.elements;

public class UnionElement extends GroupGraphPatternElement {

	public UnionElement(TriplePatternElement... triplePatternElements) {
		super(triplePatternElements);
		// TODO Auto-generated constructor stub
	}
	
	public String toString()
	{
		return "UNION " + groupPatternToString();
	}

}
