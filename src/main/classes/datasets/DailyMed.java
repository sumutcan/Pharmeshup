package main.classes.datasets;

import main.classes.dataaccess.ILinkedDataAccess;

public class DailyMed implements IDataSet {

	private ILinkedDataAccess linkedDataAccess;

	public DailyMed(ILinkedDataAccess linkedDataAccess) {
		
		this.linkedDataAccess = linkedDataAccess;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getData() {
		
		
		
	}

}
