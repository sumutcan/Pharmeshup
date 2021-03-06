package main.classes.threads;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;


import main.classes.dataaccess.remote.SparqlQueryRepo;
import main.classes.entities.SearchResult;
import main.classes.indexer.IndexUtil;
import main.classes.utils.Config;
import main.classes.utils.QueryUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class IndexFileCreatorThread implements Runnable {
	
	private File f = null;
	private Date currentDate = null;
	
	public IndexFileCreatorThread(File f, Date currentDate)
	{
		this.f = f;
		this.currentDate = currentDate;
	}

	@Override
	public void run() {
		
		long a = System.currentTimeMillis();
		Query q = null;
		try {
			q = QueryFactory.create(SparqlQueryRepo.getInstance().getCreateIndexFileQuery());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet results = null;
		try {
			results = QueryUtil.getInstance().executeRemoteSelect("dbpedia", q);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();
		int i = 0;
		while (results.hasNext()) {
			QuerySolution row = results.next();
			
			SearchResult searchResult = new SearchResult();
			searchResult.setDrugbankID(row.getLiteral("drugbankID"));
			searchResult.setDrugName(row.getLiteral("label").toString());
			searchResult.setCasNumber(row.getLiteral("?casNumber"));
			searchResult.setDrugSubject(row.getResource("s").toString());
			
			Query q2 = null;
			try {
				q2 = QueryFactory.create(SparqlQueryRepo.getInstance().getWikiPageRedirectsQuery(searchResult.getDrugSubject()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			if (searchResult.getDrugbankID() != null) {
//				ResultSet pageRedirects = null;
//				try {
//					pageRedirects = QueryUtil.getInstance()
//							.executeRemoteSelect("dbpedia", q2);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				while (pageRedirects.hasNext()) {
//					QuerySolution row2 = pageRedirects.next();
//					searchResult.addWikiPageRedirect(row2.getResource("?page"));
//				}
//
//				System.out.println(i + " "
//						+ searchResult.getWikiPageRedirects());
//				i++;
//			}
			
			searchResults.add(searchResult);

		}

		Gson gson = new Gson();
		Type listOfSearchResults = new TypeToken<ArrayList<SearchResult>>() {
		}.getType();
		String json = gson.toJson(searchResults, listOfSearchResults);

		try {
			FileWriter fw = null;
			fw = new FileWriter(f, false);
			fw.write(json);
			fw.close();
			Config.getInstance().setLastUpdateDate(currentDate);
			IndexUtil.getInstance().clearAllIndexedResults();
			System.out.println("Index file created "+currentDate);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println((System.currentTimeMillis()-a)/(1000*60));
		
	}

}
