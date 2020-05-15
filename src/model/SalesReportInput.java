package model;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SalesReportInput {

	private String type;

	private JRBeanCollectionDataSource bookDataSource;

	public SalesReportInput(String type) {

		this.type =  type;

	}

	public void setBookDataSource(JRBeanCollectionDataSource bookDataSource) {
	       this.bookDataSource = bookDataSource;
	   }

	public String getType() {
	       return type;
	   }

	public JRBeanCollectionDataSource getStudentDataSource() {
	       return bookDataSource;
	   }

	public Map<String, Object> getParameters() {
	       Map<String,Object> parameters = new HashMap<>();
	       parameters.put("P_TYPE", getType());

	       return parameters;
	   }

	 public Map<String, Object> getDataSources() {
	       Map<String,Object> dataSources = new HashMap<>();
	       dataSources.put("bookDataSource", bookDataSource);

	       return dataSources;
	   }







}
