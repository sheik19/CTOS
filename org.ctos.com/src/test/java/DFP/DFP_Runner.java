package DFP;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

import Configuration.DFB_cofiguration;
import Utilities.DataRead;
import Utilities.ExtentReport;
import Utilities.TestUtils;




public class DFP_Runner extends DFP_Baseclass {
    
	DFP_Baseclass base=new DFP_Baseclass();
	public static DataRead data; 
	

	/*
	 * Initializing the API Resource Calls
	 * all the Below calls are belong to post call only
	 * This is the Main test that we are going to execute
	 * @author Sheik
	 */

	

	@Test(dataProvider="risk_segmentation",enabled=false,dataProviderClass=DFP_data_provider.class)
	public void Risk_segmentation(String Testcase,String request,String statuscode,String risk,String Module) throws FilloException, FileNotFoundException, IOException, ParseException  {
		try {

			//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			data = new DataRead(Testcase);
			data.DFP_Readdata();
			Testcase = data.recordset.getField("Description");
			request=data.recordset.getField("TestData");
			ExtentReport.test=TestUtils.reportconfig(Testcase,DFP_constants.tester,DFP_constants.pro_name);
			DFP_Test.risk_segmentation(Testcase,request,statuscode,risk,Module);

		}
		catch(Exception e) {
			Assert.fail(e.toString());
			System.out.println(e);
		}
	}

	@Test(dataProvider="Modules_combination",enabled=false,dataProviderClass=DFP_data_provider.class)
	public void Modules_Testing(String Testcase,String request,String statuscode,String message,String Module) throws FilloException, FileNotFoundException, IOException, ParseException  {
		try {

			//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			DataRead data = new DataRead(Testcase);
			data.DFP_Readdata();
			Testcase = data.recordset.getField("Description");
			request=data.recordset.getField("TestData");
			
			ExtentReport.test=TestUtils.reportconfig(Testcase,DFP_constants.tester,DFP_constants.pro_name);
			DFP_Test.Modules_Testing(Testcase,request,statuscode,message,Module);

		}
		catch(Exception e) {
			Assert.fail(e.toString());
			System.out.println(e);
		}
	}

	
	@Test(dataProvider="Error_validation",enabled=true,dataProviderClass=DFP_data_provider.class)
	public void Error_Validation(String Testcase,String request,String statuscode,String message) throws FilloException, FileNotFoundException, IOException, ParseException  {
		try {

			//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			DataRead data = new DataRead(Testcase);
			data.DFP_Readdata();
			Testcase = data.recordset.getField("Description");
			request=data.recordset.getField("TestData");
			
			ExtentReport.test=TestUtils.reportconfig(Testcase,DFP_constants.tester,DFP_constants.pro_name);
			DFP_Test.Error_validation(Testcase,request,statuscode,message);

		}
		catch(Exception e) {
			Assert.fail(e.toString());
			System.out.println(e);
		}
	}




	

}
