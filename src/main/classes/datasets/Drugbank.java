package main.classes.datasets;

import java.util.ArrayList;

import main.classes.Enzyme;
import main.classes.Link;
import main.classes.dataaccess.ILinkedDataAccess;


public class Drugbank implements IDataSet {
	
	private String drugbankID;
	private ArrayList<String> synonyms;
	private String casNumber;
	private String description;
	private Link drugbankPage;
	private String clearence;
	private String halfTime;
	private ArrayList<Enzyme> enzymes;
	private String routeOfElimination;
	private String volumeOfDistribution;
	private String moleculeWeightMono;
	private String moleculeWeightAvg;
	private String absorption;
	
	public Drugbank(ILinkedDataAccess linkedDataAccess) {
		
		this.linkedDataAccess = linkedDataAccess;
		this.enzymes = new ArrayList<Enzyme>();
		this.synonyms = new ArrayList<String>();
	}
	
	@Override
	public void getData() throws Exception {
		
		try {
			linkedDataAccess.getDrugBankData(this);
		} catch (Exception e) {
			throw new Exception("An error occured while retrieving Drugbank data: "+ e.getMessage());
		}
		
	}
	
	
	public String getDrugbankID() {
		return drugbankID;
	}

	public void setDrugbankID(String drugbankID) {
		this.drugbankID = drugbankID;
	}

	public String getCasNumber() {
		return casNumber;
	}

	public void setCasNumber(String casNumber) {
		this.casNumber = casNumber;
	}

	public Link getDrugbankPage() {
		return drugbankPage;
	}

	public void setDrugbankPage(Link drugbankPage) {
		this.drugbankPage = drugbankPage;
	}

	public String getClearence() {
		return clearence;
	}

	public void setClearence(String clearence) {
		this.clearence = clearence;
	}

	public String getHalfTime() {
		return halfTime;
	}

	public void setHalfTime(String halfTime) {
		this.halfTime = halfTime;
	}

	public String getRouteOfElimination() {
		return routeOfElimination;
	}

	public void setRouteOfElimination(String routeOfElimination) {
		this.routeOfElimination = routeOfElimination;
	}

	public String getVolumeOfDistribution() {
		return volumeOfDistribution;
	}

	public void setVolumeOfDistribution(String volumeOfDistribution) {
		this.volumeOfDistribution = volumeOfDistribution;
	}

	public String getMoleculeWeightMono() {
		return moleculeWeightMono;
	}

	public void setMoleculeWeightMono(String moleculeWeightMono) {
		this.moleculeWeightMono = moleculeWeightMono;
	}

	public String getMoleculeWeightAvg() {
		return moleculeWeightAvg;
	}

	public void setMoleculeWeightAvg(String moleculeWeightAvg) {
		this.moleculeWeightAvg = moleculeWeightAvg;
	}

	public ILinkedDataAccess getLinkedDataAccess() {
		return linkedDataAccess;
	}

	public void setLinkedDataAccess(ILinkedDataAccess linkedDataAccess) {
		this.linkedDataAccess = linkedDataAccess;
	}

	public ArrayList<String> getSynonyms() {
		return synonyms;
	}

	public ArrayList<Enzyme> getEnzymes() {
		return enzymes;
	}

	public String getAbsorption() {
		return absorption;
	}

	public void setAbsorption(String absorption) {
		this.absorption = absorption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private ILinkedDataAccess linkedDataAccess;
	
	


}
