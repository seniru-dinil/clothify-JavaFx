package edu.icet.clothify.util.report;

import edu.icet.clothify.database.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class ReportGenerator {

    public  void generateOrderReport() {
        try {
            String reportPath = "src/main/resources/reports/invoice.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
            String outputFilePath = "order_report.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFilePath);
            System.out.println("Report generated successfully: " + outputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static ReportGenerator instance;

    private ReportGenerator(){}

    public static ReportGenerator getInstance(){
        if (instance==null) instance=new ReportGenerator();
        return instance;
    }
}
