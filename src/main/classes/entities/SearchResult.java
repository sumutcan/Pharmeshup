package main.classes.entities;

import java.util.ArrayList;


import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

public class SearchResult {
	
	public String getDrugName() {
		return drugName;
	}
	
	public void setDrugName(String drugName) {
		
		if (drugName.contains("@")) 
		{
			String[] temp = drugName.split("@");
			this.drugName = temp[0];
		}
		else
			this.drugName = drugName;
	}
	
	public String getDrugbankID() {
		return drugbankID;
	}
	
	public void setDrugbankID(Literal literal) {
		if (literal != null)
			this.drugbankID = literal.getString();
		else
			literal = null;
	}
	public String getCasNumber() {
		return casNumber;
	}

	public void setCasNumber(Literal literal) {
		
		if (literal != null)
			this.casNumber = literal.getString();
	}
	
	public String getDrugSubject() {
		return drugSubject;
	}
	
	public void setDrugSubject(String drugSubject) {
		this.drugSubject = drugSubject;
	}
	
	public ArrayList<String> getWikiPageRedirects() {
		return wikiPageRedirects;
	}

	public String toString()
	{
		return drugName;
	}
	
	
	private String drugSubject;
	private String drugName;
	private String drugbankID;
	private String casNumber;

	private ArrayList<String> wikiPageRedirects = new ArrayList<String>();
	public void addWikiPageRedirect(Resource resource) {
		
		if (resource != null)
			if (!wikiPageRedirects.contains(resource.getLocalName()))
				wikiPageRedirects.add(resource.getLocalName());
		
	}

	
	

}
