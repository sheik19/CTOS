package Utilities;

import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public static ExtentReports reports;
	public static ExtentSparkReporter htmlreport;
	public static ExtentTest test;
	public static String currentDir = System.getProperty("user.dir");

	//To avoid external initialization
	public ExtentReport(String report,String reportname,String documenttitle) {
		reports = new ExtentReports();
		htmlreport = new ExtentSparkReporter(report);
		reports.attachReporter(htmlreport);
		htmlreport.config().setReportName(reportname);
		htmlreport.config().setDocumentTitle(documenttitle);
		htmlreport.config().setTheme(Theme.STANDARD);
		System.out.println(reports);
		
	}
	
	@SuppressWarnings("static-access")
	public ExtentReport(ExtentTest test) {
		test=this.test;
	}
	
	
	
	@BeforeTest
	public void Tokens() {
		
		
		
	}
	/*
	 *create the Report generation path,create object for every Test
	 * @author Sheik
	 */
	public static void initialize(String report,String reportname,String documenttitle)
	{
		new ExtentReport(report,reportname,documenttitle);
	}
	
	
	
	

}
