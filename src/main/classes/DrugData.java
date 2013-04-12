package main.classes;

public class DrugData {

	public DrugData(SearchResult selectedValue) {
		
		this.dbpediaData = new DBPedia();
		this.drugBankdata = new Drugbank();
		this.dailymedData = new DailyMed();
		
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
