package model;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CustomerSalesReportInput {

	private JRBeanCollectionDataSource customerDataSource;

	public void setBookDataSource(JRBeanCollectionDataSource customerDataSource) {
	       this.customerDataSource = customerDataSource;
	   }


	public JRBeanCollectionDataSource getStudentDataSource() {
	       return this.customerDataSource;
	   }

	public Map<String, Object> getParameters() {
	       Map<String,Object> parameters = new HashMap<>();
	       parameters.put("P_TYPE", "Customer_Sales");

	       return parameters;
	   }

	public Map<String, Object> getDataSources() {
	       Map<String,Object> dataSources = new HashMap<>();
	       dataSources.put("customerDataSource", customerDataSource);

	       return dataSources;
	   }

}
