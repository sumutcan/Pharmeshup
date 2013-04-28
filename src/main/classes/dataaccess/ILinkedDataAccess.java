package main.classes.dataaccess;

import main.classes.datasets.DBPedia;
import main.classes.datasets.Drugbank;

import com.hp.hpl.jena.query.ResultSet;

public interface ILinkedDataAccess {

	void getDBPediaData(DBPedia dbPedia) throws Exception;

	void getDrugBankData(Drugbank drugbank) throws Exception;

}
