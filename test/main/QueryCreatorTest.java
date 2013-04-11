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
		assertEquals(2, qc.searchInIndexFile("naprox").size()); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		assertEquals(sdf.parse("12-04-2013"), Config.getInstance().getLastUpdateDate());
	
	}
	
	

}
