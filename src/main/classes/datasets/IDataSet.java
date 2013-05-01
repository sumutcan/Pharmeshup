package main.classes.datasets;

public interface IDataSet {
	
	void getData() throws Exception;
	void setKeys(IDataSet dataset);
}
