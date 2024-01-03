package DFP;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import Utilities.ExtentReport;
/*
 * This class contains all Assertions of the Test case
 * if you want to Add your Assertion in future based on requirement expectation result
 * call this assertion methods to teh Test Flow cases and compare it
 * @author Sheik
 */

//ASSERTIONS
public class Verify_userDetails {
	
	public static void verify_status_code(String actualstatusCode,String expectstatusCode) {
		if(actualstatusCode.equalsIgnoreCase(expectstatusCode)) {
			ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Expected StatusCode is "+ actualstatusCode +" and got the same", ExtentColor.GREEN));	
		}
		else {
			ExtentReport.test.log(Status.FAIL, MarkupHelper.createLabel("Expected StatusCode is "+ actualstatusCode +"not displaying the expected response" , ExtentColor.RED));
		}
		
	}
	
	
	public static void verify_login_ID(String actualloginID,String expectedloginID) {
		if(actualloginID.equalsIgnoreCase(expectedloginID)) {
			ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Expected LoginID is "+ actualloginID+" and got the same", ExtentColor.GREEN));	
		}
		
		else {
			ExtentReport.test.log(Status.FAIL, MarkupHelper.createLabel("Expected LoginID is "+ actualloginID+"not displaying the expected response\" " , ExtentColor.RED));
		}
	}
	
	public static void verify_res_Message(String actualmessage,String expectedmessage) {
		if(actualmessage.equalsIgnoreCase(expectedmessage)) {
			ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Expected Response Message is "+ actualmessage+" and got the same in the response", ExtentColor.GREEN));	
		}
		
		else {
			ExtentReport.test.log(Status.FAIL, MarkupHelper.createLabel("Expected Response Message is "+ expectedmessage+"but the actual message is :" +actualmessage, ExtentColor.RED));
		}
	}
	
	public static void verify_Token(String actualmessage,String expectedmessage) {
		if(actualmessage.equalsIgnoreCase(expectedmessage)) {
			ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Expected Response Message is "+ actualmessage+" and got the same in the response", ExtentColor.GREEN));	
		}
		
		else {
			ExtentReport.test.log(Status.FAIL, MarkupHelper.createLabel("Expected Response Message is "+ expectedmessage+"but the actual message is :" +actualmessage, ExtentColor.RED));
		}
	}
	
	//below scripts to change the ErrorMessage modification
	//(To checking the actualmessage and expectedmessage, we can verify the error message)
	public static void Verify_Errors(String actualmessage,String expectedmessage) {
		if(actualmessage.equalsIgnoreCase(expectedmessage)) {
		
			ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Expected Error Message is "+ actualmessage+" and got the same in the response", ExtentColor.GREEN));	
		}
		
		else {
			ExtentReport.test.log(Status.FAIL, MarkupHelper.createLabel("Expected Error Message is "+actualmessage +"but the actual message is :" +expectedmessage, ExtentColor.RED));
		}
	}
	//below scripts to change the ErrorCode modification
	public static void Verify_ErrorCode(String actualerrorCode,String expectederrorCode) {
		if(actualerrorCode.equalsIgnoreCase(expectederrorCode)) {
			
			ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Expected ErrorCode is "+ actualerrorCode+" and got the same in the response", ExtentColor.GREEN));	
		}
		
		else {
			ExtentReport.test.log(Status.FAIL, MarkupHelper.createLabel("Expected ErrorCode is "+ actualerrorCode+"but the actual message is :" +expectederrorCode, ExtentColor.RED));
		}
	}
	
	
	

}
