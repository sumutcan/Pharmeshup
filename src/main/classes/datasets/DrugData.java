package main.classes.datasets;

import main.classes.dataaccess.LinkedDataAccessFactory;
import main.classes.entities.SearchResult;
import main.classes.threads.ThreadHandler;

public class DrugData {


	

	public DrugData(SearchResult selectedValue) {
		
		
		this.dbpediaData = new DBPedia(LinkedDataAccessFactory.createLinkedDataAccess(), selectedValue);
		this.drugBankdata = new Drugbank(LinkedDataAccessFactory.createLinkedDataAccess(), selectedValue);
		this.dailymedData = new DailyMed(LinkedDataAccessFactory.createLinkedDataAccess(), selectedValue);
		
	}
	
	public void retrieve() throws Exception
	{
		
		ThreadHandler.getInstance().retrieve(dbpediaData,drugBankdata);
		
	}
	public void construct() throws Exception
	{
		ThreadHandler.getInstance().construct(dbpediaData,drugBankdata);
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
	private ADataSet drugBankdata;
	private ADataSet dbpediaData;
	private ADataSet dailymedData;
	

	
}
