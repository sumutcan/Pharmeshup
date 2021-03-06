package main.classes.dataaccess.remote;


import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

import main.classes.dataaccess.ILinkedDataAccess;
import main.classes.datasets.DBPedia;
import main.classes.datasets.Drugbank;
import main.classes.entities.Enzyme;
import main.classes.entities.Link;
import main.classes.utils.QueryUtil;

public class RemoteDataAccess implements ILinkedDataAccess {

	@Override
	public void getDBPediaData(DBPedia dbPedia) throws Exception {

		if (QueryUtil.getInstance().pingEndpoint("dbpedia")) {
			Query q = QueryFactory.create(SparqlQueryRepo.getInstance()
					.getDBPediaDataQuery(dbPedia.getDrugName()));

			ResultSet results = QueryUtil.getInstance().executeRemoteSelect(
					"dbpedia", q);

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
	}

	@Override
	public void getDrugBankData(Drugbank drugbank) throws Exception {

		if (QueryUtil.getInstance().pingEndpoint("drugbank")) {

			Query q = QueryFactory.create(SparqlQueryRepo.getInstance()
					.getDrugbankQuery(drugbank));
			ResultSet results = QueryUtil.getInstance().executeRemoteSelect(
					"drugbank", q);

			while (results.hasNext()) {
				QuerySolution row = results.next();
				drugbank.setDescription(row.getLiteral("?description")
						.getString());
				drugbank.addSynonym(row.getLiteral("?synonym"));
				drugbank.addRelatedLinks(new Link(row.getResource("?link")));
				drugbank.setAbsorption(row.getLiteral("?absorption"));
				drugbank.setClearence(row.getLiteral("?clearance"));
				drugbank.setRouteOfElimination(row.getLiteral("?roe"));
				drugbank.setVolumeOfDistribution(row.getLiteral("?vod"));
				drugbank.setHalfTime(row.getLiteral("?halfLife"));
				drugbank.addEnzyme(new Enzyme(row.getResource("?enzyme"), row.getLiteral("?enzymeName")));
				
			}
		}
		if (QueryUtil.getInstance().pingEndpoint("drugbank2")) {
				
				Query q = QueryFactory.create(SparqlQueryRepo.getInstance().getDrugbank2Query(drugbank));
				ResultSet results = QueryUtil.getInstance().executeRemoteSelect("drugbank2", q);
				
				while (results.hasNext())
				{
					QuerySolution row = results.next();
					if (drugbank.getDescription() == null)
						drugbank.setDescription(row.getLiteral("?description").getString());
					drugbank.addSynonym(row.getLiteral("?synonym"));
					if (drugbank.getAbsorption() == null)
						drugbank.setAbsorption(row.getLiteral("?absorption"));
					if (drugbank.getClearence() == null)
						drugbank.setClearence(row.getLiteral("?clearance"));
					drugbank.setMoleculeWeightAvg(row.getLiteral("?molWeightAvg"));
					drugbank.setMoleculeWeightMono(row.getLiteral("?molWeightMono"));
					if (drugbank.getHalfTime() == null)
						drugbank.setHalfTime(row.getLiteral("?halfLife"));
					

				}
			
			}

		}

	@Override
	public void getEnzymeData(Enzyme enzyme) throws Exception {
		
		if (QueryUtil.getInstance().pingEndpoint("drugbank"))
		{
			Query q = QueryFactory.create(SparqlQueryRepo.getInstance().getDrugbankEnzymesQuery(enzyme.getSubject().toString()));
			ResultSet results = QueryUtil.getInstance().executeRemoteSelect("drugbank", q);
			
			while (results.hasNext())
			{
				QuerySolution row = results.next();
				enzyme.setCellularLocation(row.getLiteral("?cellularLoc"));
				enzyme.setMolecularWeight(row.getLiteral("?molWeight"));
				enzyme.setReaction(row.getLiteral("?reaction"));
				enzyme.setSpecificFunction(row.getLiteral("?specificFunction"));
				enzyme.setTheoreticalPi(row.getLiteral("?theoPi"));
				
			}
		}
		
	}
	public void constructDBPediaData(DBPedia dbPedia) throws Exception
	{
		Query q = QueryFactory.create(SparqlQueryRepo.getInstance().getDBPediaConstructQuery(dbPedia.getDrugName()));
		Model graph = QueryUtil.getInstance().executeRemoteConstruct(q, "dbpedia");
		QueryUtil.getInstance().storeModel(graph, dbPedia.getDrugName()+"_"+dbPedia.getClass().getSimpleName());
	}

	}

