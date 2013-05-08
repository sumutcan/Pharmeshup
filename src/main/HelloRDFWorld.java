package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;

import main.classes.utils.QueryUtil;

import com.hp.hpl.jena.graph.Graph;
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
import com.querymanager.elements.LangFilterElement;
import com.querymanager.elements.TriplePatternElement;




public class HelloRDFWorld {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String folder = "/home/umutcan/rdfdata";
//		String source = "/home/umutcan/Metamizole.n3";
		
		
		
		Dataset dataset = TDBFactory.createDataset(folder);
		
		Model tdb = dataset.getDefaultModel();
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
		String q = "BASE <http://dbpedia.org/resource/> PREFIX owl:  <http://www.w3.org/2002/07/owl#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> construct { ?s rdfs:label ?o} where {?s rdfs:label ?o . ?o <bif:contains> \"naproxen\". FILTER ( lang(?o) = \"en\" )} limit 10";
		
		String c = SparqlQueryManager.getInstance().createQuery().addBase("http://dbpedia.org/resource/")
				.addPrefix("owl", "http://www.w3.org/2002/07/owl#")
				.addConstruct(new TriplePatternElement("<Naproxen>", "?p", "?o"))
				.addTriplePattern("<Naproxen>", "?p", "?o")
				.buildQueryString();
		
		String a = SparqlQueryManager.getInstance().createQuery().addBase("http://dbpedia.org/resource/")
				.addPrefix("owl", "http://www.w3.org/2002/07/owl#")
				.addSelectParamaters(ISparqlQuery.DISTINCT, "?p","?o")
				.addTriplePattern("<Naproxen>", "?p", "?o")
				.buildQueryString();
		
		ISparqlQuery query5 = SparqlQueryManager.getInstance().createQuery();
		QueryUtil
				.getInstance()
				.getCommonPrefixes(query5)
				.addSelectParamaters(true, "?s", "?label", "?drugbankID",
						"?casNumber")
				.addFiltredTriplePattern("?s", "rdfs:label", "?label",
						new LangFilterElement("?label", ISparqlQuery.LANG_EN))
				.addOptionalPattern(
						new TriplePatternElement("?s", "dbpprop:drugbank",
								"?drugbankID"))
				.addOptionalPattern(
						new TriplePatternElement("?s", "dbpedia-owl:casNumber",
								"?casNumber"))
				.addGroupGraphPattern(
						new TriplePatternElement("?s", "rdf:type",
								"dbpedia-owl:Drug"))
				.addUnionPattern(
						new TriplePatternElement("?s", "rdf:type",
								"dbpedia-owl:ChemicalCompound"));

		String b = query5.buildQueryString();
		
		
			
		Query query = QueryFactory.create(c);
//		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		QueryExecution qexec = QueryExecutionFactory.create(query, tdb);
//		Model results = qexec.execConstruct();
//		
//		ResultSet results = qexec.execSelect();
//		ResultSetFormatter.out(System.out, results, query);
		
		Model m = qexec.execConstruct();
		m.write(System.out);
	
		
//		FileOutputStream fos = new FileOutputStream(new File("/home/umutcan/temp.n3"));
//		results.write(fos, "N-TRIPLE");
//		results.write(System.out, "TURTLE");
//		
//		dataset.begin(ReadWrite.WRITE);
//		
//		FileManager.get().readModel(tdb, "/home/umutcan/temp.n3");
//		tdb.commit();
//		dataset.commit();
//		
//		tdb.close();
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
