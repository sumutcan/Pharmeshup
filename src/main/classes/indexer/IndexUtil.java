package main.classes.indexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;



import main.classes.dataaccess.remote.SparqlQueryRepo;
import main.classes.entities.SearchResult;
import main.classes.threads.IndexFileCreatorThread;
import main.classes.threads.ThreadHandler;
import main.classes.utils.Config;
import main.classes.utils.QueryUtil;

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

		
		if (allIndexedResults.isEmpty()) {

			BufferedReader bfr = new BufferedReader(new FileReader(idxPath));

			Type listOfSearchResults = new TypeToken<ArrayList<SearchResult>>() {
			}.getType();
			allIndexedResults = new Gson().fromJson(bfr, listOfSearchResults);
			
			if (allIndexedResults == null)
				allIndexedResults = new ArrayList<SearchResult>();
			
			bfr.close();
		}

		ArrayList<SearchResult> foundResults = new ArrayList<SearchResult>();

		for (SearchResult s : allIndexedResults)
		{
			
			if (s.getDrugName().toLowerCase()
					.contains(searchTerm.toLowerCase())) {

				foundResults.add(s);
			}
			else
			{
				for (String wiki : s.getWikiPageRedirects())
				{
					if (wiki.toLowerCase().contains(searchTerm.toLowerCase()))
					{
						foundResults.add(s);
						break;
					}
				}
			}
		}
		
		Date lastUpdateDate = Config.getInstance().getLastUpdateDate();
		Date currentDate = new Date();

		if (currentDate.getTime() / (1000 * 60 * 60 * 24)
				- lastUpdateDate.getTime() / (1000 * 60 * 60 * 24) > 7) {
			createIndexFile(currentDate);
			
			
		}

		return foundResults;
	}
	
	public void clearAllIndexedResults()
	{
		synchronized (allIndexedResults) {
			allIndexedResults.clear();
		}
	}
	

	private void createIndexFile(Date currentDate) throws Exception {


		File f = new File(idxPath);

		if (!f.exists())
			f.createNewFile();

		ThreadHandler.getInstance().createIndexFile(f, currentDate);
		

	}
	
}
