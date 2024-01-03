package CID;

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


/*
 * Initializing the API Resource Calls
 * all the Below calls are belong to post call only
 * This is the Main test that we are going to execute
 * @author Sheik
 */

//Runner Class[TestCases Starts from this class]

public class CID_Runner extends CID_Baseclass {
    
	CID_Baseclass base=new CID_Baseclass();
	public static DataRead data; 
	

	

	

	@Test(priority = 0,   dataProvider="Existing_customer",enabled=false,dataProviderClass=CID_data_provider.class)
	public void Existing_customer(String Testcase,String request,String statuscode,String content1,String content2) throws Throwable  {
		try {

			//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			data = new DataRead(Testcase);
			data.CID_Readdata();
			String Test=data.recordset.getField("Testcase");
			Testcase = data.recordset.getField("Description");
			request=data.recordset.getField("TestData");
			ExtentReport.test=TestUtils.reportconfig(Testcase,CID_constants.tester,CID_constants.pro_name);
			if(Test.equalsIgnoreCase("Existing_customer")) {
			CID_Test.Existing_customer(Testcase,request,statuscode,content1,content2);
			}
			else if(Test.equalsIgnoreCase("User_Dashboard")) {
				CID_Test.User_Dashboard(Testcase, request, statuscode, content1, content2);	
			}
			else if(Test.equalsIgnoreCase("view_PDF")) {
				CID_Test.View_pdf(Testcase, request, statuscode, content1);	
			}
			else if(Test.equalsIgnoreCase("purchase_ScoreReport")) {
				CID_Test.purchase_scoreReport(Testcase, request, statuscode, content1,content2);	
			}
			else if(Test.equalsIgnoreCase("payment_confirmation")) {
 				CID_Test.payment_confirmation(Testcase, request, statuscode, content1,content2);	
			}

		}
		catch(Exception e) {
			Assert.fail(e.toString());
			System.out.println(e);
		}
	}

	@Test(priority = 1, dataProvider="New_customer",enabled=true,dataProviderClass=CID_data_provider.class)
	
	public void User_customer(String Testcase,String request,String statuscode,String content1,String content2) throws Throwable  {
		try {

			//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			data = new DataRead(Testcase);
			data.CID_Readdata();
			String Test=data.recordset.getField("Testcase");
			Testcase = data.recordset.getField("Description");
			request=data.recordset.getField("TestData");
			ExtentReport.test=TestUtils.reportconfig(Testcase,CID_constants.tester,CID_constants.pro_name);
			//CID_Test.User_Dashboard(Testcase,request,statuscode, content1,content2);
			if(Test.equalsIgnoreCase("New_customer")) {
				CID_Test.New_customer(Testcase, request, statuscode, content2, statuscode);
				}
				else if(Test.equalsIgnoreCase("User_Dashboard")) {
					CID_Test.User_Dashboard(Testcase, request, statuscode, content1, content2);	
				}
				else if(Test.equalsIgnoreCase("view_PDF")) {
					CID_Test.View_pdf(Testcase, request, statuscode, content1);	
				}
				else if(Test.equalsIgnoreCase("purchase_ScoreReport")) {
					CID_Test.purchase_scoreReport(Testcase, request, statuscode, content1,content2);	
				}
				else if(Test.equalsIgnoreCase("payment_confirmation")) {
					CID_Test.payment_confirmation(Testcase, request, statuscode, content1,content2);	
				}

		}
		catch(Exception e) {
			Assert.fail(e.toString());
			System.out.println(e);
		}
	}
	
@Test(priority = 2, dataProvider="Negative_Testing",enabled=false,dataProviderClass=CID_data_provider.class)
	
	public void Negative_Testing(String Testcase,String request,String statuscode,String statusmessage,String errorCode) throws Throwable  {
		try {

			//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			data = new DataRead(Testcase);
			data.CID_Readdata();
			String Test=data.recordset.getField("Testcase");
			Testcase = data.recordset.getField("Description");
			request=data.recordset.getField("TestData");
			ExtentReport.test=TestUtils.reportconfig(Testcase,CID_constants.tester,CID_constants.pro_name);
			//CID_Test.User_Dashboard(Testcase,request,statuscode, content1,content2);
			CID_Test.Negative_Test(Testcase, request, statuscode, statusmessage,errorCode);

		}
		catch(Exception e) {
			Assert.fail(e.toString());
			System.out.println(e);
		}
	}
	
	
@Test(priority = 2, dataProvider="view_PDF",enabled=false,dataProviderClass=CID_data_provider.class)
	
	public void View_PDF(String Testcase,String request,String statuscode,String content1,String content2) throws Throwable  {
		try {

			//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			data = new DataRead(Testcase);
			data.CID_Readdata();
			Testcase = data.recordset.getField("Description");
			//request=data.recordset.getField("TestData");
			ExtentReport.test=TestUtils.reportconfig(Testcase,CID_constants.tester,CID_constants.pro_name);
			CID_Test.View_pdf(Testcase,request,statuscode,content1);

		}
		catch(Exception e) {
			Assert.fail(e.toString());
			System.out.println(e);
		}
	}

@Test(priority = 3, dataProvider="purchase_scoreReport",enabled=false,dataProviderClass=CID_data_provider.class)

public void purchase_scoreReport(String Testcase,String request,String statuscode,String content1,String content2) throws FilloException, FileNotFoundException, IOException, ParseException  {
	try {

		//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		data = new DataRead(Testcase);
		data.CID_Readdata();
		Testcase = data.recordset.getField("Description");
		request=data.recordset.getField("TestData");
		ExtentReport.test=TestUtils.reportconfig(Testcase,CID_constants.tester,CID_constants.pro_name);
		CID_Test.purchase_scoreReport(Testcase,request,statuscode,content1,content2);

	}
	catch(Exception e) {
		Assert.fail(e.toString());
		System.out.println(e);
	}
}

@Test(priority = 4, dataProvider="payment_Confirmation",enabled=false,dataProviderClass=CID_data_provider.class)

public void payment_Confirmation(String Testcase,String request,String statuscode,String content1,String content2) throws FilloException, FileNotFoundException, IOException, ParseException  {
	try {

		//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		data = new DataRead(Testcase);
		data.CID_Readdata();
		Testcase = data.recordset.getField("Description");
		request=data.recordset.getField("TestData");
		ExtentReport.test=TestUtils.reportconfig(Testcase,CID_constants.tester,CID_constants.pro_name);
		CID_Test.payment_confirmation(Testcase,request,statuscode,content1,content2);

	}
	catch(Exception e) {
		Assert.fail(e.toString());
		System.out.println(e);
	}
}



	

}
