package main.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	private String idxPath = System.getProperty("user.dir") + "/config/pharmashup.idx";
	private ArrayList<String> allIndexedLabels = new ArrayList<String>();

	public static synchronized QueryCreator getInstance() {
		if (instance == null)
			instance = new QueryCreator();

		return instance;
	}

	private QueryCreator() {
	}

	public ArrayList<String> searchInIndexFile(String searchTerm)
			throws Exception {

		Date lastUpdateDate = Config.getInstance().getLastUpdateDate();
		Date currentDate = new Date();

		if (currentDate.getTime() / (1000 * 60 * 60 * 24)
				- lastUpdateDate.getTime() / (1000 * 60 * 60 * 24) > 1) {
			createIndexFile();
			Config.getInstance().setLastUpdateDate(currentDate);
			allIndexedLabels.clear();
		}

		if (allIndexedLabels.isEmpty()) {
			
			BufferedReader bfr = new BufferedReader(new FileReader(idxPath));

			while (bfr.ready())
				allIndexedLabels.add(bfr.readLine());
			
			bfr.close();
		}
		
		ArrayList<String> foundLabels = new ArrayList<String>();
		
		for (String s : allIndexedLabels)
			if (s.toLowerCase().contains(searchTerm.toLowerCase()))
			{
				String[] temp = s.split("@");
				String label = temp[0];
				foundLabels.add(label);
			}

		return foundLabels;
	}

	private void createIndexFile() throws Exception {

		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
		query.addPrefix("dbpedia-owl", "http://dbpedia.org/ontology/")
				.addPrefix("dbpprop", "http://dbpedia.org/property/")
				.addPrefix("foaf", "http://xmlns.com/foaf/0.1/")
				.addPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#")
				.addPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
				.addSelectParamaters(true,"?s", "?label")
				.addFiltredTriplePattern("?s", "rdfs:label", "?label",
						new FilterElement("langMatches(lang(?label), \"en\")"))
				.addGroupGraphPattern(
						"?s",
						"rdf:type",
						"dbpedia-owl:Drug",
						new UnionElement("?s", "rdf:type",
								"dbpedia-owl:ChemicalCompound"));

		Query q = QueryFactory.create(query.buildQueryString());

		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://dbpedia.org/sparql", q);

		ResultSet results = qexec.execSelect();

		File f = new File(idxPath);
		FileWriter fw = new FileWriter(f, false);

		BufferedWriter bfw = new BufferedWriter(fw);

		while (results.hasNext()) {
			QuerySolution qSol = results.next();
			bfw.write(qSol.getLiteral("?label").toString() + "\n");
		}

		bfw.close();

	}

}
