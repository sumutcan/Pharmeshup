package com.querymanager.elements;

public class GraphElement {

	private String varOrIRIRef;
	private TriplePatternElement[] args;
	
	
	public GraphElement(String varOrIRIRef, TriplePatternElement[] args) {
		
		this.varOrIRIRef = varOrIRIRef;
		this.args = args;
		
	}
	
	public String toString()
	{
		String graphString = "\tGRAPH "+varOrIRIRef+"\n{\n";
		for (TriplePatternElement tpe : args)
			graphString += tpe;
			
		graphString +="}\n";
		
		return graphString;
	}

	
}

