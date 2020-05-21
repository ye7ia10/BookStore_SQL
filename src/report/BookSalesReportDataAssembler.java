package report;

import java.util.ArrayList;
import model.SalesReport;
import model.SalesReportInput;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class BookSalesReportDataAssembler {

	public static SalesReportInput assemble(ArrayList<SalesReport> bookList, String type) {

		SalesReportInput bookReportInput = new SalesReportInput(type);

		JRBeanCollectionDataSource bookDataSource = new JRBeanCollectionDataSource(bookList, false);
		bookReportInput.setBookDataSource(bookDataSource);

	    return bookReportInput;

	}

}
