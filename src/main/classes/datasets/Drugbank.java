package main.classes.datasets;

import java.util.ArrayList;

import com.hp.hpl.jena.rdf.model.Literal;

import main.classes.dataaccess.ILinkedDataAccess;
import main.classes.entities.Enzyme;
import main.classes.entities.Link;
import main.classes.entities.SearchResult;


public class Drugbank extends ADataSet {
	
	private ArrayList<String> synonyms;
	private String description;
	private ArrayList<Link> relatedLinks;
	private String clearence;
	private String halfTime;
	private ArrayList<Enzyme> enzymes;
	private String routeOfElimination;
	private String volumeOfDistribution;
	private String moleculeWeightMono;
	private String moleculeWeightAvg;
	private String absorption;
	
	public Drugbank(ILinkedDataAccess linkedDataAccess, SearchResult selectedValue) {
		
		
		super(linkedDataAccess, selectedValue);
		
		this.enzymes = new ArrayList<Enzyme>();
		this.synonyms = new ArrayList<String>();
		this.relatedLinks = new ArrayList<Link>();
	}
	
	@Override
	public void getData() throws Exception {
		
		try {
			getLinkedDataAccess().getDrugBankData(this);
		} catch (Exception e) {
			throw new Exception("An error occured while retrieving Drugbank data: "+ e.getMessage());
		}
		
	}
	

	public ArrayList<Link> getRelatedLinks() {
		return relatedLinks;
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
	

	public void addSynonym(Literal literal) {
		
		if (literal != null)
			if (!synonyms.contains(literal.getString()))
				synonyms.add(literal.getString());
	}

	public void addRelatedLinks(Link link) {
		
			if (!relatedLinks.contains(link))
				relatedLinks.add(link);
	}

	
	


}
