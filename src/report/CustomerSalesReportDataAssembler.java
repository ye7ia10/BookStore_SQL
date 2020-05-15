package report;

import java.util.ArrayList;

import model.CustomerSalesReport;
import model.CustomerSalesReportInput;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CustomerSalesReportDataAssembler {

	public static CustomerSalesReportInput assemble(ArrayList<CustomerSalesReport> customerList, String type) {

		CustomerSalesReportInput bookReportInput = new CustomerSalesReportInput();

		JRBeanCollectionDataSource customerDataSource = new JRBeanCollectionDataSource(customerList, false);
		bookReportInput.setBookDataSource(customerDataSource);

	    return bookReportInput;

	}

}
