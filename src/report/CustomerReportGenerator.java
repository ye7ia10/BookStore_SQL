package report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.CustomerSalesReport;
import model.CustomerSalesReportInput;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;

public class CustomerReportGenerator {

	public void generateReport(ArrayList<CustomerSalesReport> customerList) throws IOException {


		CustomerSalesReportInput customerReportInput = CustomerSalesReportDataAssembler.assemble(customerList);

		// Compile jrxml file.
		   JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager.compileReport("src\\report\\customerSalesTemplate.jrxml");
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    @SuppressWarnings("unused")
		byte[] reportData = null;

	       JasperPrint jasperPrint = null;
	       try {
	           JRMapArrayDataSource dataSource = new JRMapArrayDataSource(new Object[]{customerReportInput.getDataSources()});


	           jasperPrint = JasperFillManager.fillReport(jasperReport,
	        		   customerReportInput.getParameters(), dataSource);

	           reportData = JasperExportManager.exportReportToPdf(jasperPrint);
	       } catch (JRException e) {
	           e.printStackTrace();
	       } catch (Exception e) {
	           e.printStackTrace();
	       }

	       // Make sure the output directory exists.
	       File outDir = new File("src\\report\\output");
	       outDir.mkdirs();

	       // Export to PDF.
	       try {
			JasperExportManager.exportReportToPdfFile(jasperPrint,
			           "src\\report\\output\\CustomerSalesReport.pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	       System.out.println("Done!");
	   }

}
