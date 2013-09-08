package main.classes.dataaccess.local;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.tdb.TDBFactory;

import main.classes.dataaccess.ILinkedDataAccess;
import main.classes.dataaccess.remote.SparqlQueryRepo;
import main.classes.datasets.DBPedia;
import main.classes.datasets.Drugbank;
import main.classes.entities.Enzyme;
import main.classes.entities.Link;
import main.classes.utils.QueryUtil;

public class LocalDataAccess implements ILinkedDataAccess {

	@Override
	public void getDBPediaData(DBPedia dbPedia)  throws Exception {
		
			
			Query q = QueryFactory.create(SparqlQueryRepo.getInstance()
					.getDBPediaDataQuery(dbPedia.getDrugName()));

			ResultSet results = QueryUtil.getInstance().executeLocalSelect(
					dbPedia.getDrugName()+"_"+dbPedia.getClass().getSimpleName(), q);

			while (results.hasNext()) {
				QuerySolution row = results.next();
				if (row.getResource("?drugbankID") != null)
					dbPedia.setDrugbankID(row.getResource("?drugbankID")
							.getLocalName());
				dbPedia.setDescription(row.getLiteral("abstract").getString());
				dbPedia.setWikiPage(new Link(row.getResource("wikiPage")
						.toString()));
				
			}
		

		
		
	}

	@Override
	public void getDrugBankData(Drugbank drugbank) throws Exception {
		
		
		
	}

	@Override
	public void getEnzymeData(Enzyme enzyme) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
