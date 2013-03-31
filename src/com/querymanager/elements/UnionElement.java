package com.querymanager.elements;

public class UnionElement extends GroupGraphPatternElement {

	public UnionElement(String s, String p, String o) {
		super(s, p, o);
		// TODO Auto-generated constructor stub
	}
	
	public String toString()
	{
		return "\tUNION " + groupPatternToString() + "\n";
	}

}
