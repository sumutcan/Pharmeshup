package main.classes.datasets;

import main.classes.dataaccess.ILinkedDataAccess;


public class Drugbank implements IDataSet {

	private ILinkedDataAccess linkedDataAccess;
	
	public Drugbank(ILinkedDataAccess linkedDataAccess) {
		
		this.linkedDataAccess = linkedDataAccess;
		
	}

	@Override
	public void getData() {
		// TODO Auto-generated method stub
		
	}

}
