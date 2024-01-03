package TestData;

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

public class TestData_baseclass {
	
	public static String user_dir=System.getProperty("user.dir");
	public static String x_api_key,env;
	
	
	/*
	 * BeforeSuite is the first method,after initiate the run
	 * @author Sheik
	 */
	@Parameters({"env"})
	@BeforeSuite
	
	public void setUpSuite(String  env) {
		DFB_cofiguration DFBconfig=new DFB_cofiguration(System.getProperty("user.dir")+"\\Properties\\DFB_config.properties");		
		this.env=env;  
		switch(env)
	        {
	        case "DFB_SIT_env":
	        	
                this.env=DFBconfig.get_DFB_SIT_env();
                x_api_key=DFBconfig.get_DFB_SIT_x_api_key();	            
	            break;
	            
	        case "DFB_Dev_env":
	        	
	        	env=DFBconfig.get_DFB_Dev_env();
                x_api_key=DFBconfig.get_DFB_Dev_x_api_key();
	            break;
	            
            case "UAT_Dev_env":
	        	
	        	env=DFBconfig.get_DFB_Dev_env();
                x_api_key=DFBconfig.get_DFB_Dev_x_api_key();
	            break;
	            
	        default :
	            System.out.println("Environmet is not selected, please set the Environment");     

	        }
		
		
		ExtentReport.initialize(TestData_constant.reports,TestData_constant.reportname,TestData_constant.DocumentTitle);
		
		
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
