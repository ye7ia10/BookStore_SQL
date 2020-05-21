package report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.SalesReport;
import model.SalesReportInput;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;

public class BookReportGenerator {

	public void generateReport(ArrayList<SalesReport> bookList, String type) throws IOException {


		SalesReportInput bookReportInput = BookSalesReportDataAssembler.assemble(bookList, type);

		// Compile jrxml file.
		   JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager.compileReport("src\\report\\bookSalesReportTemplate.jrxml");
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	       JasperPrint jasperPrint = null;
	       try {
	           JRMapArrayDataSource dataSource = new JRMapArrayDataSource(new Object[]{bookReportInput.getDataSources()});


	           jasperPrint = JasperFillManager.fillReport(jasperReport,
	        		   bookReportInput.getParameters(), dataSource);


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
			           "src\\report\\output\\BookSalesReport_" + type + ".pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	       System.out.println("Done!");
	   }



}
