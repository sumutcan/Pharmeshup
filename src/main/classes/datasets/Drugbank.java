package main.classes.datasets;

import java.util.ArrayList;

import com.hp.hpl.jena.rdf.model.Literal;

import main.classes.dataaccess.ILinkedDataAccess;
import main.classes.entities.Enzyme;
import main.classes.entities.Link;
import main.classes.entities.SearchResult;
import main.classes.utils.DataClearUtil;


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

	public void setClearence(Literal literal) {
		if (literal != null)
			this.clearence = literal.getString();
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

	public void setRouteOfElimination(Literal literal) {
		if (literal != null)
			this.routeOfElimination = DataClearUtil.hexToString(literal.getString());
	}

	public String getVolumeOfDistribution() {
		return volumeOfDistribution;
	}

	public void setVolumeOfDistribution(Literal literal) {
		if (literal != null)		
			this.volumeOfDistribution = DataClearUtil.hexToString(literal.getString());
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

	public void setAbsorption(Literal literal) {
		
		if (literal != null)
			this.absorption = DataClearUtil.hexToString(literal.getString());
		else
			this.absorption = null;
	}

	public String getDescription() {
			
		return description;
	}

	public void setDescription(String description) {
		this.description =  DataClearUtil.hexToString(description);
	}
	

	public void addSynonym(Literal literal) {
		
		if (literal != null)
			if (!synonyms.contains(literal.getString()))
				synonyms.add(DataClearUtil.hexToString(literal.getString()));
	}

	public void addRelatedLinks(Link link) {
		
			if (!relatedLinks.contains(link))
				relatedLinks.add(link);
	}

	
	


}
