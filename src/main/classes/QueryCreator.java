package main.classes;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

public class QueryCreator {

	private static QueryCreator instance = null;
	private String idxPath = System.getProperty("user.dir")
			+ "/config/idx.json";
	private ArrayList<SearchResult> allIndexedResults = new ArrayList<SearchResult>();

	public static synchronized QueryCreator getInstance() {
		if (instance == null)
			instance = new QueryCreator();

		return instance;
	}

	private QueryCreator() {
	}

	public ArrayList<SearchResult> searchInIndexFile(String searchTerm)
			throws Exception {

		Date lastUpdateDate = Config.getInstance().getLastUpdateDate();
		Date currentDate = new Date();

		if (currentDate.getTime() / (1000 * 60 * 60 * 24)
				- lastUpdateDate.getTime() / (1000 * 60 * 60 * 24) > 1) {
			createIndexFile();
			Config.getInstance().setLastUpdateDate(currentDate);
			allIndexedResults.clear();
		}

		if (allIndexedResults.isEmpty()) {

			BufferedReader bfr = new BufferedReader(new FileReader(idxPath));

			Type listOfSearchResults = new TypeToken<ArrayList<SearchResult>>() {
			}.getType();
			allIndexedResults = new Gson().fromJson(bfr, listOfSearchResults);

			bfr.close();
		}

		ArrayList<SearchResult> foundResults = new ArrayList<SearchResult>();

		for (SearchResult s : allIndexedResults)
			if (s.getDrugName().toLowerCase()
					.contains(searchTerm.toLowerCase())) {

				foundResults.add(s);
			}

		return foundResults;
	}

	private void createIndexFile() throws Exception {

		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
		query.addPrefix("dbpedia-owl", "http://dbpedia.org/ontology/")
				.addPrefix("dbpprop", "http://dbpedia.org/property/")
				.addPrefix("foaf", "http://xmlns.com/foaf/0.1/")
				.addPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#")
				.addPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
				.addSelectParamaters(true, "?s", "?label", "?drugbankID")
				.addFiltredTriplePattern("?s", "rdfs:label", "?label",
						new FilterElement("langMatches(lang(?label), \"en\")"))
				.addGroupGraphPattern(
						"?s",
						"rdf:type",
						"dbpedia-owl:Drug",
						new UnionElement("?s", "rdf:type",
								"dbpedia-owl:ChemicalCompound"))
				.addOptionalPattern("?s", "dbpprop:drugbank", "?drugbankID");

		Query q = QueryFactory.create(query.buildQueryString());

		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://dbpedia.org/sparql", q);

		ResultSet results = qexec.execSelect();

		File f = new File(idxPath);

		if (!f.exists())
			f.createNewFile();

		FileWriter fw = new FileWriter(f, false);

		ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();
		while (results.hasNext()) {
			QuerySolution row = results.next();

			SearchResult searchResult = new SearchResult();
			searchResult.setDrugbankID(row.getLiteral("drugbankID"));
			searchResult.setDrugName(row.getLiteral("label").toString());
			searchResult.setDrugSubject(row.getResource("s").toString());

			searchResults.add(searchResult);

		}

		Gson gson = new Gson();
		Type listOfSearchResults = new TypeToken<ArrayList<SearchResult>>() {
		}.getType();
		String json = gson.toJson(searchResults, listOfSearchResults);

		fw.write(json);

		fw.close();

	}

}
