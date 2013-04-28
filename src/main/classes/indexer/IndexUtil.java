package main.classes.indexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import main.classes.Config;

import main.classes.QueryUtil;
import main.classes.SearchResult;
import main.classes.SparqlQueryRepo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class IndexUtil {

	private String idxPath = System.getProperty("user.dir")
			+ "/config/idx.json";
	private ArrayList<SearchResult> allIndexedResults = new ArrayList<SearchResult>();
	
	private static IndexUtil instance = null;


	public static synchronized IndexUtil getInstance() {
		if (instance == null)
			instance = new IndexUtil();

		return instance;
	}
	
	private IndexUtil() {}
	
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


		Query q = QueryFactory.create(SparqlQueryRepo.getInstance().getCreateIndexFileQuery());

		ResultSet results = QueryUtil.getInstance().executeRemoteSelect("dbpedia", q);

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
