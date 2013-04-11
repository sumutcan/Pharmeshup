package main;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import main.classes.Config;
import main.classes.QueryCreator;

import org.junit.Test;

public class QueryCreatorTest {

	@Test
	public void testSearchInIndexFile() throws Exception {
		
		QueryCreator qc = QueryCreator.getInstance();
		assertEquals(true, qc.searchInIndexFile("nap").contains("Naproxen@en")); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		assertEquals(sdf.parse("11-04-2013"), Config.getInstance().getLastUpdateDate());
	
	}
	
	

}
