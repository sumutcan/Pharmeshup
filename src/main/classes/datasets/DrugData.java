package main.classes.datasets;

import main.classes.dataaccess.LinkedDataAccessFactory;
import main.classes.entities.SearchResult;

public class DrugData {


	

	public DrugData(SearchResult selectedValue) {
		
		
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
	

	
}
