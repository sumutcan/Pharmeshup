package main.classes.dataaccess;

import main.classes.datasets.DBPedia;
import main.classes.datasets.Drugbank;
import main.classes.entities.Enzyme;


public interface ILinkedDataAccess {

	void getDBPediaData(DBPedia dbPedia) throws Exception;

	void getDrugBankData(Drugbank drugbank) throws Exception;
	
	void getEnzymeData(Enzyme enzyme) throws Exception;


}
