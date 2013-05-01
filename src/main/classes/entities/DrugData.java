package main.classes.entities;

import main.classes.dataaccess.LinkedDataAccessFactory;
import main.classes.datasets.DBPedia;
import main.classes.datasets.DailyMed;
import main.classes.datasets.Drugbank;
import main.classes.datasets.IDataSet;

public class DrugData {


	private SearchResult searchResult;

	public DrugData(SearchResult selectedValue) {
		
		this.searchResult = selectedValue;
		this.dbpediaData = new DBPedia(LinkedDataAccessFactory.createLinkedDataAccess(), selectedValue);
		this.drugBankdata = new Drugbank(LinkedDataAccessFactory.createLinkedDataAccess());
		this.dailymedData = new DailyMed(LinkedDataAccessFactory.createLinkedDataAccess());
		
	}
	
	public void retrieve() throws Exception
	{
		
		dbpediaData.getData();
		drugBankdata.setKeys(dbpediaData);
		drugBankdata.getData();
		
	}
	
	public Drugbank getDrugBankdata() {
		return (Drugbank) drugBankdata;
	}
	public DBPedia getDbpediaData() {
		return (DBPedia) dbpediaData;
	}
	public DailyMed getDailymedData() {
		return (DailyMed) dailymedData;
	}
	private IDataSet drugBankdata;
	private IDataSet dbpediaData;
	private IDataSet dailymedData;
	
	public SearchResult getSearchResult() {
		// TODO Auto-generated method stub
		return searchResult;
	}
	
}
