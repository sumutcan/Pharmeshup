package main.classes;

import com.hp.hpl.jena.rdf.model.Literal;

public class SearchResult {
	
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		
		if (drugName.contains("@")) 
		{
			String[] temp = drugName.split("@");
			String label = temp[0];
			this.drugName = label;
		}
		
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
	
	public String getDrugSubject() {
		return drugSubject;
	}
	public void setDrugSubject(String drugSubject) {
		this.drugSubject = drugSubject;
	}
	
	public String toString()
	{
		return drugName;
	}
	
	private String drugSubject;
	private String drugName;
	private String drugbankID;
	

}
