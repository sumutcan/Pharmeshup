package main.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Config {

	private static Config instance = null;
	private Properties props = new Properties();
	private String configSource =System.getProperty("user.dir")+"/config";
	
	public static synchronized Config getInstance()
	{
		if (instance == null)
			instance = new Config();
		
		return instance;
	}
	
	private Config()
	{
		
	}

	public Date getLastUpdateDate() throws Exception {
		
		
		
		try {
			
			props.load(new FileInputStream(new File(configSource+"/config.properties")));
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			
			return df.parse(props.getProperty("lastAccessDate"));
			
		} catch (FileNotFoundException e) {
			
			throw new Exception("Config file cannot be found: "+ e.getMessage());
		}
	}

	public void setLastUpdateDate(Date currentDate) throws Exception {
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			props.load(new FileInputStream(new File(configSource+"/config.properties")));
			props.setProperty("lastAccessDate", df.format(currentDate)); 
			props.store(new FileOutputStream(configSource+"/config.properties"), null);
			
		} catch (FileNotFoundException e) {
			
			throw new Exception("Config file cannot be found: "+ e.getMessage());
		}
		
	}
}
