package main.classes.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.classes.datasets.ADataSet;

public class DataRetriever implements Callable<Object> {

	private ADataSet dataset;
	
	public DataRetriever(ADataSet dataset) {
		this.dataset = dataset;
	}

	@Override
	public Object call() throws Exception {
		
			dataset.getData();
		
		return null;
	}


}
