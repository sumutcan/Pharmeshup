package main.classes.dataaccess.remote;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import main.classes.Link;
import main.classes.QueryUtil;
import main.classes.SparqlQueryRepo;
import main.classes.dataaccess.ILinkedDataAccess;
import main.classes.datasets.DBPedia;
import main.classes.datasets.Drugbank;

public class RemoteDataAccess implements ILinkedDataAccess{

	@Override
	public void getDBPediaData(DBPedia dbPedia) throws Exception {
		
		Query q = QueryFactory.create(SparqlQueryRepo.getInstance().getDBPediaDataQuery(dbPedia.getDrugName()));
		
		ResultSet results = QueryUtil.getInstance().executeRemoteSelect("dbpedia", q);
		
		while (results.hasNext())
		{
			QuerySolution row = results.next();
			if (row.getResource("?drugbankID") != null)
				dbPedia.setDrugbankID(row.getResource("?drugbankID").getLocalName());
			dbPedia.setDescription(row.getLiteral("abstract").getString());
			dbPedia.setCasNumber(row.getLiteral("casNumber"));
			dbPedia.setWikiPage(new Link(dbPedia.getDrugName()+ " at Wikipedia", row.getResource("wikiPage").toString()));
		}
		
		
		
		
	}

	@Override
	public void getDrugBankData(Drugbank drugbank) throws Exception {
		
		Query q = QueryFactory.create(SparqlQueryRepo.getInstance().getDrugbankQuery(drugbank));
		ResultSet results = QueryUtil.getInstance().executeRemoteSelect("drugbank", q);
		
		while (results.hasNext())
		{
			QuerySolution row = results.next();
			drugbank.setDescription(row.getLiteral("?description").getString());
		}
		
		
	}

}
