package main.classes;

import java.util.ArrayList;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.querymanager.ISparqlQuery;
import com.querymanager.SparqlQueryManager;
import com.querymanager.elements.FilterElement;
import com.querymanager.elements.UnionElement;

public class RemoteDataAccess implements ILinkedDataAccess {

	@Override
	public ArrayList<SearchResult> getData(String searchTerm) throws Exception {

		ArrayList<SearchResult> searchResults =  new ArrayList<SearchResult>();;
		for (String label : QueryCreator.getInstance()
				.searchInIndexFile(searchTerm)) {
			
			ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
			query.addPrefix("dbpedia-owl", "http://dbpedia.org/ontology/")
					.addPrefix("dbpprop", "http://dbpedia.org/property/")
					.addPrefix("foaf", "http://xmlns.com/foaf/0.1/")
					.addPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#")
					.addPrefix("rdf",
							"http://www.w3.org/1999/02/22-rdf-syntax-ns#")
					.addSelectParamaters(true, "?s", "?drugbankID")
					.addTriplePattern("?s", "rdfs:label", "\""+label+"\"@en")
					.addGroupGraphPattern(
							"?s",
							"rdf:type",
							"dbpedia-owl:Drug",
							new UnionElement("?s", "rdf:type",
									"dbpedia-owl:ChemicalCompound"))
					.addOptionalPattern("?s", "dbpprop:drugbank", "?drugbankID");
			
			//String s = query.buildQueryString();
			Query q = QueryFactory.create(query.buildQueryString());

			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", q);

			ResultSet results = qexec.execSelect();

			
			while (results.hasNext()) {
				QuerySolution row = results.next();
				SearchResult searchResult = new SearchResult();
				searchResult.setDrugbankID(row.getLiteral("drugbankID"));
				searchResult.setDrugName(label);
				searchResult.setDrugSubject(row.getResource("s").toString());

				searchResults.add(searchResult);

			}
		}
		return searchResults;

	}
}
