package main.classes;

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
		query.addPrefix("dbpedia-owl", "http://dbpedia.org/ontology/")
				.addPrefix("dbpprop", "http://dbpedia.org/property/")
				.addPrefix("foaf", "http://xmlns.com/foaf/0.1/")
				.addPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#")
				.addPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
				.addSelectParamaters(true, "?s", "?label", "?drugbankID","?drugbankIDAlt")
				.addFiltredTriplePattern("?s", "rdfs:label", "?label",
						new FilterElement(ISparqlQuery.LANG_EN))
				.addGroupGraphPattern(
						"?s",
						"rdf:type",
						"dbpedia-owl:Drug",
						new UnionElement("?s", "rdf:type",
								"dbpedia-owl:ChemicalCompound"))
				.addOptionalPattern("?s", "dbpprop:drugbank", "?drugbankID")
				.addOptionalPattern("?s", "dbpprop:drugbank", "?drugbankIDAlt");
		
		return query.buildQueryString();

	}
	public String getDBPediaDataQuery(String drugName) throws Exception
	{
		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
		
		QueryCreator.getInstance().getCommonPrefixes(query)
				.addSelectParamaters(true, "?abstract", "?casNumber", "?wikiPage")
				.addTriplePattern("?s", "rdfs:label", "\""+drugName+"\"@en")
				.addFiltredTriplePattern("?s", "dbpedia-owl:abstract", "?abstract",new LangFilterElement("?abstract",ISparqlQuery.LANG_EN))
				.addOptionalPattern("?s", "dbpedia-owl:casNumber", "?casNumber")
				.addOptionalPattern("?s", "foaf:isPrimaryTopicOf", "?wikiPage");
		
		return query.buildQueryString();
	}
}
