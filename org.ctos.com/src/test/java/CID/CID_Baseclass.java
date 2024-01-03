package CID;

import org.testng.annotations.BeforeMethod;
import java.io.IOException;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Configuration.DFB_cofiguration;
import Utilities.ExtentReport;



/*
 * Base class for all the Testcases, Before executing the test 
 * this class object should get encountered
 * @author Sheik
 */

public class CID_Baseclass {
	
	public static String user_dir=System.getProperty("user.dir");
	public static String x_api_key,env,genUser,genUser_Dashboard,view_pdf;
	
	
	/*
	 * BeforeSuite is the first method,after initiate the run
	 * @author Sheik
	 */
	@Parameters({"env"})
	@BeforeSuite
	
	public void setUpSuite(String  env) {
		
		
		ExtentReport.initialize(CID_constants.reports,CID_constants.reportname,CID_constants.DocumentTitle);
		
		
	}


	
	@AfterSuite
	public void afterSuite() throws IOException  {
		
		ExtentReport.reports.flush();

	}

	

	@AfterMethod
	public void setUp() {
		
		
		
	}
	
	@BeforeMethod
	public void get_data() {
		
	}



	
	
}
