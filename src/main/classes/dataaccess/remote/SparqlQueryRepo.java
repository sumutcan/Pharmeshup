package main.classes.dataaccess.remote;

import main.classes.datasets.Drugbank;
import main.classes.utils.QueryUtil;

import com.querymanager.ISparqlQuery;
import com.querymanager.SparqlQueryManager;
import com.querymanager.elements.FilterElement;
import com.querymanager.elements.LangFilterElement;
import com.querymanager.elements.TriplePatternElement;
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
				.addOptionalPattern(new TriplePatternElement("?s", "dbpprop:drugbank", "?drugbankID"))
				.addGroupGraphPattern(new TriplePatternElement("?s","rdf:type","dbpedia-owl:Drug"))
				.addUnionPattern(new TriplePatternElement("?s", "rdf:type", "dbpedia-owl:ChemicalCompound"));
				
		
		return query.buildQueryString();

	}
	public String getDBPediaDataQuery(String drugName) throws Exception
	{
		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
		
		QueryUtil.getInstance().getCommonPrefixes(query)
				.addSelectParamaters(true, "?abstract", "?casNumber", "?wikiPage","?drugbankID")
				.addTriplePattern("?s", "rdfs:label", "\""+drugName+"\"@en")
				.addFiltredTriplePattern("?s", "dbpedia-owl:abstract", "?abstract",new LangFilterElement("?abstract",ISparqlQuery.LANG_EN))
				.addOptionalPattern(new TriplePatternElement("?s", "dbpedia-owl:casNumber", "?casNumber"))
				.addOptionalPattern(new TriplePatternElement("?s", "foaf:isPrimaryTopicOf", "?wikiPage"))
				.addOptionalPattern(new TriplePatternElement("?s", "owl:sameAs", "?drugbankID",new FilterElement("regex(str(?drugbankID),\"drugbank\",\"i\")")));
		
		
		return query.buildQueryString();
	}

	public String getDrugbankQuery(Drugbank drugbank) throws Exception {
		
		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
		QueryUtil.getInstance().getCommonPrefixes(query).addSelectParamaters(true, "?description", "?synonym");
		query.addTriplePattern("?s", "dc:description", "?description")
		.addOptionalPattern(new TriplePatternElement("?s", "drugbankvoca:synonym", "?synonym"))
		.addGroupGraphPattern(new TriplePatternElement("drugbankbio:"+drugbank.getDrugbankID(), "owl:sameAs", "drugbankbio:"+drugbank.getDrugbankID()))
		.addUnionPattern(new TriplePatternElement("?s", "drugbankvoca:xref", "bioCas:"+drugbank.getCasNumber()))
		.addUnionPattern(new TriplePatternElement("?s", "rdfs:label","?label", new FilterElement("regex(?label, \""+drugbank.getDrugName()+"\",\"i\")")));
		
		
		
		return query.buildQueryString();
		
		
		
		
	}
}
