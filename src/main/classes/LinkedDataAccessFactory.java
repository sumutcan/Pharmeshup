package main.classes;

public class LinkedDataAccessFactory {

	
	public static ILinkedDataAccess createLinkedDataAccess()
	{
		return new RemoteDataAccess();
	}
}
