package main.classes.dataaccess;


import main.classes.dataaccess.remote.RemoteDataAccess;
import main.classes.datasets.Datasets;

public class LinkedDataAccessFactory {

	
	public static ILinkedDataAccess createLinkedDataAccess()
	{
		return new RemoteDataAccess();
	}

}
