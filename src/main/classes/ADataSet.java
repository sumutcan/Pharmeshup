package main.classes;

public abstract class ADataSet {

	private ILinkedDataAccess linkedDataAccess;
	
	public ADataSet(ILinkedDataAccess linkedDataAccess)
	{
		this.linkedDataAccess = linkedDataAccess;
	}
	
	public abstract void getData() throws Exception;
	
}
