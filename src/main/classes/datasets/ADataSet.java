package main.classes.datasets;

import main.classes.dataaccess.ILinkedDataAccess;
import main.classes.entities.SearchResult;



public abstract class ADataSet {

	private String drugName;
	private String casNumber;
	private String drugbankID;
	private ILinkedDataAccess linkedDataAccess;


	
	public ADataSet(ILinkedDataAccess linkedDataAccess, SearchResult selectedValue) {
		
		this.linkedDataAccess = linkedDataAccess;
		setCasNumber(selectedValue.getCasNumber());
		setDrugbankID(selectedValue.getDrugbankID());
		setDrugName(selectedValue.getDrugName());
	}
	
	public ILinkedDataAccess getLinkedDataAccess() {
		return linkedDataAccess;
	}



	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getCasNumber() {
		return casNumber;
	}

	public void setCasNumber(String casNumber) {
		
			this.casNumber = casNumber;
	}

	public String getDrugbankID() {
		return drugbankID;
	}

	
	public abstract void getData() throws Exception;
		


	public void setDrugbankID(String drugbankID) {
		
		if (this.drugbankID == null)
			this.drugbankID = drugbankID;
		
		
	}


}