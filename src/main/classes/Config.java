package main.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


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

	public String getEndpoint(String name) throws Exception {
		
		try {
			props.load(new FileInputStream(new File(configSource+"/endpoints.properties")));
			return props.getProperty(name);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new Exception("Config file cannot be found: "+ e.getMessage());
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception("Error occured when reading endpoint file: "+ e.getMessage());
		}
		
		
	}
}
