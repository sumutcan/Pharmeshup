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

	public static synchronized SparqlQueryRepo getInstance() {
		if (instance == null)
			instance = new SparqlQueryRepo();

		return instance;
	}

	private SparqlQueryRepo() {
	}

	public String getCreateIndexFileQuery() throws Exception {
		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
		QueryUtil
				.getInstance()
				.getCommonPrefixes(query)
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

		return query.buildQueryString();

	}

	public String getWikiPageRedirectsQuery(String subject) throws Exception {
		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
		QueryUtil
				.getInstance()
				.getCommonPrefixes(query)
				.addSelectParamaters(true, "?page")
				.addTriplePattern("<" + subject + ">", "rdf:type",
						"dbpedia-owl:Drug")
				.addTriplePattern("?page", "dbpedia-owl:wikiPageRedirects",
						"<" + subject + ">");

		return query.buildQueryString();
	}

	public String getDBPediaDataQuery(String drugName) throws Exception {
		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();

		QueryUtil
				.getInstance()
				.getCommonPrefixes(query)
				.addSelectParamaters(true, "?abstract", "?wikiPage",
						"?drugbankID")
				.addTriplePattern("?s", "rdfs:label", "\"" + drugName + "\"@en")
				.addFiltredTriplePattern(
						"?s",
						"dbpedia-owl:abstract",
						"?abstract",
						new LangFilterElement("?abstract", ISparqlQuery.LANG_EN))
				.addOptionalPattern(
						new TriplePatternElement("?s", "foaf:isPrimaryTopicOf",
								"?wikiPage"))
				.addOptionalPattern(
						new TriplePatternElement(
								"?s",
								"owl:sameAs",
								"?drugbankID",
								new FilterElement(
										"regex(str(?drugbankID),\"drugbank\",\"i\")")));

		return query.buildQueryString();
	}

	public String getDrugbankQuery(Drugbank drugbank) throws Exception {

		if (drugbank.getDrugbankID() != null) {

			ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
			QueryUtil
					.getInstance()
					.getCommonPrefixes(query)
					.addSelectParamaters(true, "?description", "?synonym",
							"?link", "?absorption", "?clearance", "?roe","?vod","?halfLife","?enzyme","?enzymeName");
			query.addTriplePattern("drugbankbio:" + drugbank.getDrugbankID(),
					"dc:description", "?description")
					.addOptionalPattern(
							new TriplePatternElement("drugbankbio:"
									+ drugbank.getDrugbankID(),
									"drugbankvoca:synonym", "?synonym"))
					.addOptionalPattern(
							new TriplePatternElement("drugbankbio:"
									+ drugbank.getDrugbankID(), "rdfs:seeAlso",
									"?link"))
					.addOptionalPattern(
							new TriplePatternElement("drugbankbio:"
									+ drugbank.getDrugbankID(),
									"drugbankvoca:route-of-elimination", "?roe"))
					.addOptionalPattern(
							new TriplePatternElement("drugbankbio:"
									+ drugbank.getDrugbankID(),
									"drugbankvoca:volume-of-distribution", "?vod"))
					.addOptionalPattern(
							new TriplePatternElement("drugbankbio:"
									+ drugbank.getDrugbankID(),
									"drugbankvoca:half-life", "?halfLife"))
					.addOptionalPattern(new TriplePatternElement("drugbankbio:"+drugbank.getDrugbankID(),"drugbankvoca:enzyme","?enzyme"), new TriplePatternElement("?enzyme","drugbankvoca:name","?enzymeName"))
					.addOptionalPattern(
							new TriplePatternElement("drugbankbio:"
									+ drugbank.getDrugbankID(),
									"drugbankvoca:clearance", "?clearance"))
					.addOptionalPattern(
							new TriplePatternElement("drugbankbio:"
									+ drugbank.getDrugbankID(),
									"drugbankvoca:absorption", "?absorption"));

			return query.buildQueryString();

		}

		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
		QueryUtil
				.getInstance()
				.getCommonPrefixes(query)
				.addSelectParamaters(true, "?description", "?synonym", "?link",
						"?absorption", "?clearance", "?roe","?vod","?halfLife");
		query.addTriplePattern("?s", "dc:description", "?description")
				.addOptionalPattern(
						new TriplePatternElement("?s", "drugbankvoca:synonym",
								"?synonym"))
				.addOptionalPattern(
						new TriplePatternElement("?s", "rdfs:seeAlso", "?link"))
				.addOptionalPattern(
						new TriplePatternElement("?s",
								"drugbankvoca:clearance", "?clearance"))
				.addOptionalPattern(
							new TriplePatternElement("?s",
									"drugbankvoca:route-of-elimination", "?roe"))
				.addOptionalPattern(
							new TriplePatternElement("?s",
									"drugbankvoca:half-life", "?halfLife"))
				.addOptionalPattern(
							new TriplePatternElement("?s",
									"drugbankvoca:volume-of-distribution", "?vod"))
				.addOptionalPattern(
						new TriplePatternElement("?s",
								"drugbankvoca:absorption", "?absorption"))
				.addGroupGraphPattern(
						new TriplePatternElement("?s", "drugbankvoca:xref",
								"bioCas:" + drugbank.getCasNumber()));

		return query.buildQueryString();

	}

	public String getDrugbank2Query(Drugbank drugbank) throws Exception {

		if (drugbank.getDrugbankID() != null) {
			ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
			QueryUtil
					.getInstance()
					.getCommonPrefixes(query)
					.addSelectParamaters(true, "?description", "?synonym",
							"?absorption", "?clearance","?molWeightMono","?molWeightAvg","?halfLife");
			query.addTriplePattern("drugs:" + drugbank.getDrugbankID(),
					"drugbank:description", "?description")
					.addOptionalPattern(
							new TriplePatternElement("drugs:"
									+ drugbank.getDrugbankID(),
									"drugbank:synonym", "?synonym"))
					.addOptionalPattern(
							new TriplePatternElement("drugs:"
									+ drugbank.getDrugbankID(),
									"drugbank:absorption", "?absorption"))
					.addOptionalPattern(
							new TriplePatternElement("drugs:"
									+ drugbank.getDrugbankID(),
									"drugbank:molecularWeightMono", "?molWeightMono"))
					.addOptionalPattern(
							new TriplePatternElement("drugs:"
									+ drugbank.getDrugbankID(),
									"drugbank:molecularWeightAverage", "?molWeightAvg"))
					.addOptionalPattern(
							new TriplePatternElement("drugs:"
									+ drugbank.getDrugbankID(),
									"drugbank:halfLife", "?halfLife"))
					.addOptionalPattern(
							new TriplePatternElement("drugs:"
									+ drugbank.getDrugbankID(),
									"drugbank:clearance", "?clearance"));

			return query.buildQueryString();
		}

		ISparqlQuery query2 = SparqlQueryManager.getInstance().createQuery();
		QueryUtil
				.getInstance()
				.getCommonPrefixes(query2)
				.addSelectParamaters(true, "?description", "?synonym",
						"?absorption", "?clearance","?molWeightAvg");
		query2.addTriplePattern("?s", "drugbank:description", "?description")
				.addOptionalPattern(
						new TriplePatternElement("?s", "drugbank:synonym",
								"?synonym"))
				.addOptionalPattern(
						new TriplePatternElement("?s",
								"drugbank:absorption", "?absorption"))
				.addOptionalPattern(
						new TriplePatternElement("?s",
								"drugbank:clearance", "?clearance"))
				.addOptionalPattern(
							new TriplePatternElement("?s",
									"drugbank:molecularWeightMono", "?molWeightMono"))
				.addOptionalPattern(
							new TriplePatternElement("?s",
									"drugbank:molecularWeightAverage", "?molWeightAvg"))
				.addOptionalPattern(
							new TriplePatternElement("?s",
									"drugbank:halfLife", "?halfLife"))
				.addGroupGraphPattern(
						new TriplePatternElement("?s",
								"drugbank:casRegistryNumber", "bioCas:"
										+ drugbank.getCasNumber()))
				.addUnionPattern(
						new TriplePatternElement("?s", "rdfs:label", "\""
								+ drugbank.getDrugName() + "\""));

		return query2.buildQueryString();
	}
	public String getDrugbankEnzymesQuery(String subject) throws Exception
	{
		ISparqlQuery query = SparqlQueryManager.getInstance().createQuery();
		QueryUtil.getInstance().getCommonPrefixes(query)
		.addSelectParamaters(true, "?cellularLoc", "?reaction","?specificFunction","?molWeight","?theoPi")
		.addOptionalPattern(new TriplePatternElement("<"+subject+">","drugbankvoca:cellular-location","?cellularLoc"))
		.addOptionalPattern(new TriplePatternElement("<"+subject+">","drugbankvoca:reaction","?reaction"))
		.addOptionalPattern(new TriplePatternElement("<"+subject+">","drugbankvoca:specific-function","?specificFunction"))
		.addOptionalPattern(new TriplePatternElement("<"+subject+">","drugbankvoca:molecular-weight","?molWeight"))
		.addOptionalPattern(new TriplePatternElement("<"+subject+">","drugbankvoca:theoretical-pi","?theoPi"));
		
		return query.buildQueryString();
	}
}
