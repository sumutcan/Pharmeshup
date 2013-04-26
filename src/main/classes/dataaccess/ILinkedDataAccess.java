package main.classes.dataaccess;

import com.hp.hpl.jena.query.ResultSet;

public interface ILinkedDataAccess {

	ResultSet getDBPediaData(String drugName) throws Exception;

	ResultSet getDrugBankData();

}
