package main.classes.dataaccess.remote;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;

import main.classes.QueryUtil;
import main.classes.SparqlQueryRepo;
import main.classes.dataaccess.ILinkedDataAccess;

public class RemoteDataAccess implements ILinkedDataAccess{

	@Override
	public ResultSet getDBPediaData(String drugName) throws Exception {
		
		Query q = QueryFactory.create(SparqlQueryRepo.getInstance().getDBPediaDataQuery(drugName));
		return QueryUtil.getInstance().executeRemoteSelect("dbpedia", q);
		
		
	}

}
