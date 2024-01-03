package DSC;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Configuration.DFB_cofiguration;
import Configuration.DFC_configuration;
import Utilities.ExtentReport;



/*
 * Base class for all the Testcases, Before executing the test 
 * this class object should get encountered
 * @author Sheik
 */

public class DFC_baseclass {
	
	public static String user_dir=System.getProperty("user.dir");
	public static String x_api_key,env,retrieve,dataset;
	public static DFC_configuration DFCconfig=new DFC_configuration(System.getProperty("user.dir")+"\\Properties\\DFC_config.properties");		
	
	
	/*
	 * BeforeSuite is the first method,after initiate the run
	 * @author Sheik
	 */
	@Parameters({"env"})
	@BeforeSuite
	
	public void setUpSuite(String  env) {
		
		this.env=env;  
		switch(env)
	        {
	        case "DFC_SIT_env":
	        	
                this.env=DFCconfig.get_DFC_SIT_env_upload_url();
                x_api_key=DFCconfig.get_DFC_SIT_x_api_key();	
                retrieve=DFCconfig.get_DFC_SIT_env_retrieve_url();
                dataset=DFCconfig.get_dataset();
	            break;
	            
	 /*       case "DFC_Dev_env":
	        	
	        	env=DFCconfig.get_DFB_Dev_env();
                x_api_key=DFCconfig.get_DFB_Dev_x_api_key();
	            break;
	            
            case "DFC_UAT_env":
	        	
	        	env=DFCconfig.get_DFC_Dev_env();
                x_api_key=DFCconfig.get_DFC_Dev_x_api_key();
	            break;
	            */
	            
	        default :
	            System.out.println("Environmet is not selected, please set the Environment");     

	        }
		
		
		ExtentReport.initialize(user_dir+DFCconfig.get_DFC_report_path()+".html",DFCconfig.get_DFC_reportname(),DFCconfig.get_DocumentTitle());
		
		
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