package main.classes;

public class DrugData {

	private SearchResult searchResult;
	
	public SearchResult getSearchResult() {
		return searchResult;
	}

	public DrugData(SearchResult selectedValue) {
		
		this.searchResult = selectedValue;
		this.dbpediaData = new DBPedia(LinkedDataAccessFactory.createLinkedDataAccess());
		this.drugBankdata = new Drugbank(LinkedDataAccessFactory.createLinkedDataAccess());
		this.dailymedData = new DailyMed(LinkedDataAccessFactory.createLinkedDataAccess());
		
	}
	
	public void retrieve() throws Exception
	{
		dbpediaData.setDrugName(searchResult.getDrugName());
		dbpediaData.getData();
		
	}
	
	public Drugbank getDrugBankdata() {
		return drugBankdata;
	}
	public DBPedia getDbpediaData() {
		return dbpediaData;
	}
	public DailyMed getDailymedData() {
		return dailymedData;
	}
	private Drugbank drugBankdata;
	private DBPedia dbpediaData;
	private DailyMed dailymedData;
	
}
