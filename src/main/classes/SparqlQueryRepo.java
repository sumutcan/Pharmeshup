package main.classes;

import main.classes.datasets.Drugbank;

import com.querymanager.ISparqlQuery;
import com.querymanager.SparqlQueryManager;
import com.querymanager.elements.FilterElement;
import com.querymanager.elements.LangFilterElement;
import com.querymanager.elements.UnionElement;

public class SparqlQueryRepo {

	private static SparqlQueryRepo instance = null;
	
	public static synchronized SparqlQueryRepo getInstance()
	{
		if (instance == null)
			instance = new SparqlQueryRepo();
		
		return instance;
	}
	
	private SparqlQueryRepo() {}
	
	public String getCreateIndexFileQuery() throws Exception
	{
		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
		QueryUtil.getInstance().getCommonPrefixes(query)
				.addSelectParamaters(true, "?s", "?label", "?drugbankID")
				.addFiltredTriplePattern("?s", "rdfs:label", "?label",
						new LangFilterElement("?label",ISparqlQuery.LANG_EN))
				.addGroupGraphPattern(
						"?s",
						"rdf:type",
						"dbpedia-owl:Drug",
						new UnionElement("?s", "rdf:type",
								"dbpedia-owl:ChemicalCompound"))
				.addOptionalPattern("?s", "dbpprop:drugbank", "?drugbankID");
		
		return query.buildQueryString();

	}
	public String getDBPediaDataQuery(String drugName) throws Exception
	{
		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
		
		QueryUtil.getInstance().getCommonPrefixes(query)
				.addSelectParamaters(true, "?abstract", "?casNumber", "?wikiPage","?drugbankID")
				.addTriplePattern("?s", "rdfs:label", "\""+drugName+"\"@en")
				.addFiltredTriplePattern("?s", "dbpedia-owl:abstract", "?abstract",new LangFilterElement("?abstract",ISparqlQuery.LANG_EN))
				.addOptionalPattern("?s", "dbpedia-owl:casNumber", "?casNumber")
				.addOptionalPattern("?s", "foaf:isPrimaryTopicOf", "?wikiPage")
				.addFilteredOptionalPattern("?s", "owl:sameAs", "?drugbankID",new FilterElement("regex(str(?drugbankID),\"drugbank\",\"i\")"));
		
		return query.buildQueryString();
	}

	public String getDrugbankQuery(Drugbank drugbank) throws Exception {
		
		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
		QueryUtil.getInstance().getCommonPrefixes(query).addSelectParamaters(true, "?description");
		
		if (drugbank.getDrugbankID() != null)
			query.addOptionalPattern("drugbankbio:"+drugbank.getDrugbankID(), "dc:description", "?description");
		
		return query.buildQueryString();
		
		
		
		
	}
}
