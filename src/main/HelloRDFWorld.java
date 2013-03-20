package main;
import java.util.Iterator;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;
import com.querymanager.ISparqlQuery;
import com.querymanager.SparqlQueryManager;




public class HelloRDFWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		String folder = "/home/umutcan/rdfdata";
//		String source = "/home/umutcan/Metamizole.n3";
		
		
		
//	Dataset dataset = TDBFactory.createDataset(folder);
//		
//		Model tdb = dataset.getDefaultModel();
//		dataset.begin(ReadWrite.WRITE);
//		FileManager.get().readModel(tdb, source);	
//		
//		tdb.commit();
//		dataset.commit();
//		
//		tdb.close();
//		dataset.close();
		
		//Dataset dataset = TDBFactory.createDataset(folder);
//		dataset.begin(ReadWrite.READ);
//		
//		String q = "BASE <http://dbpedia.org/resource/> PREFIX owl:  <http://www.w3.org/2002/07/owl#> select * where {<Metamizole> owl:sameAs ?o} limit 100";
//		Query query = QueryFactory.create(q);
//		QueryExecution qexec = QueryExecutionFactory.create(query, tdb);
//		ResultSet results = qexec.execSelect();
//		
//		ResultSetFormatter.out(System.out, results, query);
//		
//		dataset.commit();
//		dataset.close();
		
//		ISparqlQuery q = SparqlQueryManager.getInstance().createQuery();
//		q.addPrefix("owl", "http://www.w3.org/2002/07/owl#");
//		q.addPrefix("ex", "http://example.org");
//		q.addBase("http://base.org").addSelectParamaters("?s", "?p", "?o");
//		q.addFROM("http://asdasd.com");
//		q.addTriplePattern("?name", "?mbox","\"hebele\"" );
//		System.out.println(q.buildQueryString());
	
	}

}
