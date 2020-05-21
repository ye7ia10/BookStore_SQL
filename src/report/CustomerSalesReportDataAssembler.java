package report;

import java.util.ArrayList;

import model.CustomerSalesReport;
import model.CustomerSalesReportInput;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CustomerSalesReportDataAssembler {

	public static CustomerSalesReportInput assemble(ArrayList<CustomerSalesReport> customerList) {

		CustomerSalesReportInput customerReportInput = new CustomerSalesReportInput();

		JRBeanCollectionDataSource customerDataSource = new JRBeanCollectionDataSource(customerList, false);
		customerReportInput.setBookDataSource(customerDataSource);

	    return customerReportInput;

	}

}
