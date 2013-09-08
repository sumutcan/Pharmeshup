package main.classes.dataaccess;


import main.classes.dataaccess.local.LocalDataAccess;
import main.classes.dataaccess.remote.RemoteDataAccess;
import main.classes.utils.Config;

public class LinkedDataAccessFactory {

	
	public static ILinkedDataAccess createLinkedDataAccess(String endpointName)
	{
		if (Config.getInstance().checkAvailableEndpoints(endpointName))
			return new RemoteDataAccess();
		else
			return new LocalDataAccess();
		
	}

}
