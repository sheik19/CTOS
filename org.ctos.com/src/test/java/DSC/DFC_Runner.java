package DSC;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

import DFP.DFP_Baseclass;
import DFP.DFP_Test;
import DFP.DFP_constants;
import DFP.DFP_data_provider;
import Utilities.DataRead;
import Utilities.ExtentReport;
import Utilities.TestUtils;

public class DFC_Runner {
	
	DFC_baseclass base=new DFC_baseclass();
	public static DataRead data; 
	

	/*
	 * Initializing the API Resource Calls
	 * all the Below calls are belong to post call only
	 * This is the Main test that we are going to execute
	 * @author Sheik
	 */

	

	@Test(priority=0, dataProvider = "upload_dataset",enabled = true,dataProviderClass = DFC_data_provider.class)
	public void upload_dataset(String Testcase,String request,String statuscode) throws FilloException, FileNotFoundException, IOException, ParseException  {
		try {

			//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			data = new DataRead(Testcase);
			data.DFC_Readdata();
			Testcase = data.recordset.getField("Description");
			//request=data.recordset.getField("TestData");
			ExtentReport.test=TestUtils.reportconfig(Testcase,base.DFCconfig.get_tester(),base.DFCconfig.get_pro_name());
			DFC_Test.upload_dataset_1(Testcase,request,statuscode);
			data.DFC_Ref_update(DFC_Test.refnum_str);

		}
		catch(Exception e) {
			Assert.fail(e.toString());
			System.out.println(e);
		}
	}
	
	@Test(priority=1, dataProvider = "retrieve_dataset",enabled = true,dataProviderClass = DFC_data_provider.class)
	public void retrieve_dataset(String Testcase,String ref_num,String request,String statuscode) throws FilloException, FileNotFoundException, IOException, ParseException  {
		try {

			//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			data = new DataRead(Testcase);
			data.DFC_Readdata();
			Testcase = data.recordset.getField("Description");
			//request=data.recordset.getField("TestData");
			ExtentReport.test=TestUtils.reportconfig(Testcase,base.DFCconfig.get_tester(),base.DFCconfig.get_pro_name());
			DFC_Test.retrieve_dataset_1(Testcase,ref_num,request,statuscode);

		}
		catch(Exception e) {
			Assert.fail(e.toString());
			System.out.println(e);
		}
	}
	
	

}
