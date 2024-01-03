package CID;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.codoid.products.exception.FilloException;
import com.groupdocs.comparison.internal.c.a.i.system.Threading.Thread;

import CID.Verify_userDetails;
import Utilities.DataRead;
import Utilities.ExtentReport;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * This class contains all test cases and its flow
 * if you want to Add your Test cases you can create a method here
 * then call the method in Runner class
 * @author Sheik
 */
public class CID_Test {
	
	//All the common variables are initialized in this class
	public static RequestSpecification httpRequest;
	public static String res_messagee, res_email, res_phone, res_socialIdentity, res_socialIdentityPlus, riskLevel,
			res_status, err_message, res_socialPresence_email, res_socialPresence_phone, error, res_userToken,
			ency_reportID, res_orederNum, loginID, status_code,userID,errorMessage,res_errorCode,req_email;
	public static JsonPath js;
	public static DataRead data;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static WebDriver driver;
	public static Random rand = new Random();

	
	//Existing Customer Test flow 
	public static void Existing_customer(String Testcase, String request, String statuscode, String loginid,
			String statusmessage) throws FilloException, FileNotFoundException, IOException, ParseException {
		try {
			DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();            
			String date_change=dtf.format(cal.getTime());			
			String time=date_change.replace(":","");			
			JSONParser par = new JSONParser();
			JSONObject jobj = new JSONObject(request);
			JSONObject ekycData= jobj.getJSONObject("ekycData");
			userID=ekycData.get("ekycIdNumber").toString();
			
			HashMap<String, String> formParams = new HashMap<String, String>();
			formParams.put("Authorization", "Basic QTZfeG1sOnBnZGI4MzV5aQ==");
			formParams.put("x-timeout", "10000");
			formParams.put("Content-Type", "application/json");
			Response responses;

			responses = RestAssured.given()
					.config(RestAssured.config()
					.encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("raw", ContentType.JSON)))
					.auth().basic("GSB_xml", "hrfx5bpx")
					.contentType("application/json")
					.headers(formParams).when()
					.body(jobj.toString()).post(CID_constants.genUser)
					.then().extract().response();
			 responses.getStatusCode();
			 //System.out.println(responses.asPrettyString().toString());
				js = responses.jsonPath();
				status_code = js.get("statusCode").toString();
				
				if(status_code.equalsIgnoreCase(statuscode)) {
					res_messagee = js.get("statusMessage").toString();
					res_userToken = js.get("userToken").toString();
					loginID = js.get("loginId").toString();
					Verify_userDetails.verify_status_code(status_code,statuscode);
					 Verify_userDetails.verify_login_ID(loginID,userID );
					 Verify_userDetails.verify_res_Message(res_messagee, "Existing user");
					 ExtentReport.test.pass("Response Token :"+res_userToken);
					 ExtentReport.test.info(responses.prettyPrint().toString());
					
				}
				else if(status_code.equalsIgnoreCase(statusmessage)) {
					ExtentReport.test.info(responses.prettyPrint().toString());
					error=js.get("errorMessage").toString();
					ExtentReport.test.log(Status.FAIL,
							MarkupHelper.createLabel(Testcase + " is failed due to "+error, ExtentColor.RED));
				}
				else {
					error=js.get("errorMessage").toString();
					if(status_code.equalsIgnoreCase(statusmessage)&error.equalsIgnoreCase(statusmessage))
					ExtentReport.test.log(Status.FAIL,
							MarkupHelper.createLabel(Testcase + " is failed due to "+error, ExtentColor.RED));
				}
				
				 

			

		} catch (AssertionError Ae) {

			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + Ae.getMessage(), ExtentColor.RED));
			Assert.fail(Ae.toString());

		} catch (Exception e) {
            System.out.println(e);
			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + e.getMessage(), ExtentColor.RED));
			Assert.fail(e.toString());
          
		}
	}

	//New Customer Test flow
	public static void New_customer(String Testcase, String request, String statuscode, String loginid,
			String statusmessage) throws FilloException, FileNotFoundException, IOException, ParseException {
		try {
			DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();          
			String date_change=dtf.format(cal.getTime());			
			String time=date_change.replace(":","");
			time=time.replace("/", "");
			time=time.replace(" ", "");
			//String nic=time.substring(4, 11);
			//System.out.println(nic);
			 long nic=rand.nextInt(100000);
			JSONObject jobj = new JSONObject(request);			
			JSONObject ekycData= jobj.getJSONObject("ekycData");
			String ekycIdNumber=ekycData.get("ekycIdNumber").toString();
			
			String IdNumber=ekycIdNumber.substring(0, 5)+nic;
			System.out.println(IdNumber);
			jobj.put("email", "Test"+nic+"@yopmail.com");
			ekycData.put("ekycIdNumber", IdNumber);
			userID=ekycData.get("ekycIdNumber").toString();
			req_email=jobj.get("email").toString();
            // Get the array "contactInfos"
            JSONArray contactInfos = jobj.getJSONArray("contactInfos");

            // Get the first object in the array
            JSONObject contactInfo = contactInfos.getJSONObject(0);

            // Update the value of "contactNo"
           long mobileNumber=rand.nextInt(100000000);
           System.out.println(mobileNumber);
           contactInfo.put("contactNo","60"+mobileNumber);
           
			System.out.println(jobj);
			HashMap<String, String> formParams = new HashMap<String, String>();
			formParams.put("Authorization", "Basic QTZfeG1sOnBnZGI4MzV5aQ==");
			formParams.put("x-timeout", "10000");
			formParams.put("Content-Type", "application/json");
			Response responses;

			responses = RestAssured.given()
					.config(RestAssured.config()
					.encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("raw", ContentType.JSON)))
					.auth().basic("GSB_xml", "hrfx5bpx")
					.contentType("application/json")
					.headers(formParams).when()
					.body(jobj.toString()).post(CID_constants.genUser)
					.then().extract().response();
			 responses.getStatusCode();
			 //System.out.println(responses.asPrettyString().toString());
				js = responses.jsonPath();
				status_code = js.get("statusCode").toString();
				
				if(status_code.equalsIgnoreCase(statuscode)) {
					res_messagee = js.get("statusMessage").toString();
					res_userToken = js.get("userToken").toString();
					loginID = js.get("loginId").toString();
					Verify_userDetails.verify_status_code(status_code,statuscode);
					 Verify_userDetails.verify_login_ID(loginID,userID );
					 Verify_userDetails.verify_res_Message(res_messagee, "New user created");
					 ExtentReport.test.pass("Response Token :"+res_userToken);
					 ExtentReport.test.info(responses.prettyPrint().toString());
					 email_register_and_verify_OTP(req_email,Testcase);
					
				}
				
				else {
					ExtentReport.test.info(responses.prettyPrint().toString());
					error=js.get("errorMessage").toString();
					ExtentReport.test.log(Status.FAIL,
							MarkupHelper.createLabel(Testcase + " is failed due to "+error, ExtentColor.RED));
				}
				

			

		} catch (AssertionError Ae) {

			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + Ae.getMessage(), ExtentColor.RED));
			Assert.fail(Ae.toString());

		} catch (Exception e) {
            System.out.println(e);
			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + e.getMessage(), ExtentColor.RED));
			Assert.fail(e.toString());
          
		}
	}
	
	//Email_Authentication and verify OTP Test flow
	
	public static void email_register_and_verify_OTP(String useremail,String Testcase) {
		//WebDriver driver;
		// TODO Auto-generated method stub
		try {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		//ChromeOptions chromeOptions = new ChromeOptions();
		ChromeOptions opt=new ChromeOptions();
        opt.addArguments("--remote-allow-origins=*");
        opt.addArguments("--disable-notifications");
        //Launching the browser
        driver=new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// Navigate to the demoqa website
		driver.get("https://yopmail.com/en/");
		driver.findElement(By.xpath("//input[@class='ycptinput']")).sendKeys(useremail);
		driver.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();
		ExtentReport.test.pass("Email launched Successfully with "+useremail);
		driver.switchTo().frame("ifinbox");
		driver.findElement(By.xpath("//div[text()='CTOS Identity Registration']")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("ifmail");
		String emailContent = driver.findElement(By.xpath("//main[@class='yscrollbar']")).getText();
		System.out.println(emailContent);
		String nic=driver.findElement(By.xpath("//*[@id='mail']/div/center/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td/table/tbody/tr/td/table[1]/tbody/tr/td/table/tbody/tr[1]/td[2]")).getText();		
		System.out.println(nic);	
		String password=driver.findElement(By.xpath("//*[@id='mail']/div/center/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td/table/tbody/tr/td/table[1]/tbody/tr/td/table/tbody/tr[2]/td[2]/span/strong")).getText();		
		System.out.println(password);
		String email=driver.findElement(By.xpath("//*[@id='mail']/div/center/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td/table/tbody/tr/td/table[1]/tbody/tr/td/table/tbody/tr[3]/td[2]/strong")).getText();
		System.out.println(email);
		String mobile=driver.findElement(By.xpath("//*[@id='mail']/div/center/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td/table/tbody/tr/td/table[1]/tbody/tr/td/table/tbody/tr[4]/td[2]/strong")).getText();
		System.out.println(mobile);	
		String window1=driver.getWindowHandle();
		ExtentReport.test.pass("Fetched deatails of NIC,Password,Email and MobileNumber of "+useremail);
		
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		//String window1=driver.getWindowHandle();
		//String[] content=emailContent.split(" ");
		//System.out.println(content);
		driver.switchTo().newWindow(WindowType.TAB);
		
		driver.get("https://uat-ctosid.ctos.com.my/ctosid_new/LoginPage");
		String window2=driver.getWindowHandle();
		//driver.switchTo().alert().accept();
		driver.findElement(By.xpath("//input[@id='usernameLogin']")).sendKeys(nic);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password) ;
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		ExtentReport.test.pass("launched CTOS login successfully with "+nic);
		driver.switchTo().newWindow(WindowType.TAB);
		String window3=driver.getWindowHandle();
		//Thread.sleep(3000);
		//driver.switchTo().window(window1);
		driver.get("https://yopmail.com/en/");
		driver.findElement(By.xpath("//input[@class='ycptinput']")).clear();
		driver.findElement(By.xpath("//input[@class='ycptinput']")).sendKeys(useremail);
		driver.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();
		//driver.findElement(By.xpath("//button[@id='refresh']")).click();
		//driver.findElement(By.xpath("//button[@id='refresh']")).click();
		driver.findElement(By.xpath("//button[@id='refresh']")).click();
		driver.findElement(By.xpath("//button[@id='refresh']")).click();
		driver.findElement(By.xpath("//button[@id='refresh']")).click();
		Thread.sleep(3000);
		//driver.switchTo().frame("ifinbox");
		do {
			driver.navigate().refresh();
			driver.navigate().refresh();
			driver.navigate().refresh();
			//driver.navigate().refresh();
			driver.switchTo().frame("ifinbox");
		}
		while(!driver.findElement(By.xpath("//div[@class='m'][1]//div[text()='Verify Your Login']")).isDisplayed());
			WebElement revealed = driver.findElement(By.xpath("//div[@class='m'][1]//div[text()='Verify Your Login']"));
			Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		    wait.until(d-> revealed.isDisplayed());
		
		driver.findElement(By.xpath("//div[@class='m'][1]//div[text()='Verify Your Login']")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("ifmail");
		String OTP=driver.findElement(By.xpath("//table[3]/tbody/tr/td/table/tbody/tr/td/p[2]/strong")).getText();
		System.out.println(OTP); 
		ExtentReport.test.pass("Fetched OTP of "+nic +"and the OTP is " +OTP);
		driver.switchTo().defaultContent();
		driver.switchTo().window(window2);
		driver.findElement(By.xpath("//input[@name='cacCode']")).sendKeys(OTP);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='verifyId']")).click();
		driver.findElement(By.xpath("//input[@name='currentPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='newPassword']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='confirmNewPassword']")).sendKeys("Test@123");
		driver.quit();
		}catch (AssertionError Ae) {

			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + Ae.getMessage(), ExtentColor.RED));
			Assert.fail(Ae.toString());
			driver.quit();
			

		} catch (Exception e) {
            System.out.println(e);
			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + e.getMessage(), ExtentColor.RED));
			Assert.fail(e.toString());
			driver.quit();
		}
	}
	
	//Negative Cases Test Flow
	public static void Negative_Test(String Testcase, String request, String statuscode,String statusmessage,String errorCode) throws FilloException, FileNotFoundException, IOException, ParseException {
		try {
			DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();            
			String date_change=dtf.format(cal.getTime());			
			String time=date_change.replace(":","");			
			JSONParser par = new JSONParser();
			JSONObject jobj = new JSONObject(request);
			HashMap<String, String> formParams = new HashMap<String, String>();
			formParams.put("Authorization", "Basic QTZfeG1sOnBnZGI4MzV5aQ==");
			formParams.put("x-timeout", "10000");
			formParams.put("Content-Type", "application/json");
			Response responses;

			responses = RestAssured.given()
					.config(RestAssured.config()
					.encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("raw", ContentType.JSON)))
					.auth().basic("GSB_xml", "hrfx5bpx")
					.contentType("application/json")
					.headers(formParams).when()
					.body(jobj.toString()).post(CID_constants.genUser)
					.then().extract().response();
			 responses.getStatusCode();
			 //System.out.println(responses.asPrettyString().toString());
				js = responses.jsonPath();
				status_code = js.get("statusCode").toString();
				
					res_messagee=js.get("errorMessage").toString();
					res_errorCode=js.get("errorCode").toString();
					if(status_code.equalsIgnoreCase(statuscode)&res_messagee.equalsIgnoreCase(statusmessage)) {
						ExtentReport.test.info(responses.prettyPrint().toString());
						Verify_userDetails.verify_status_code(status_code,statuscode);
						Verify_userDetails.verify_res_Message(res_messagee, statusmessage);
						Verify_userDetails.Verify_Errors(res_errorCode, errorCode);
						
						
					}
					else {
						ExtentReport.test.info(responses.prettyPrint().toString());
						ExtentReport.test.log(Status.FAIL,
								MarkupHelper.createLabel(Testcase + " is failed due to Unexpected issue  " , ExtentColor.RED));
					}
					
				
				
				
		}
				 

			

	catch (AssertionError Ae) {

			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + Ae.getMessage(), ExtentColor.RED));
			Assert.fail(Ae.toString());

		} catch (Exception e) {
            System.out.println(e);
			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + e.getMessage(), ExtentColor.RED));
			Assert.fail(e.toString());
          
		}
	}
	
   // To view the User Dash board
	public static void User_Dashboard(String Testcase, String request, String statuscode, String reportdate, String score)
			throws FilloException, FileNotFoundException, IOException, ParseException {
		try {

			HashMap<String, String> formParams = new HashMap<String, String>();
			formParams.put("x-timeout", "10000");
			formParams.put("Content-Type", "application/json");
			Response responses;

			responses = RestAssured.given()
					.config(RestAssured.config()
							.encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("raw", ContentType.JSON)))
					.header("Authorization", "Bearer " + res_userToken)
					.contentType("application/json")
					.headers(formParams)
					.when()
					.post(CID_constants.UserDashboard)
					.then().extract().response();
			//int code = responses.getStatusCode();
			 System.out.println(responses.asPrettyString().toString());
			js = responses.jsonPath();
			status_code = js.get("statusCode").toString();
			if(status_code.equalsIgnoreCase(statuscode)) {
				res_messagee = js.get("statusMessage").toString();
				//res_userToken = js.get("userToken").toString();
				loginID = js.get("userInfo.cid").toString();
				ency_reportID = js.get("scoreReportInfo.encryptedreportId").toString();
				reportdate=js.get("scoreReportInfo.reportDate").toString();
				score=js.get("scoreReportInfo.score").toString();
				 Verify_userDetails.verify_status_code(status_code,statuscode);
				 Verify_userDetails.verify_login_ID(loginID,userID );
				// Verify_userDetails.verify_res_Message(res_messagee, "Existing user");
				 ExtentReport.test.pass("user's encryptedreportId:"+ency_reportID);
				 ExtentReport.test.pass("Response Message:"+res_messagee);
				 ExtentReport.test.pass("Reported Date:"+reportdate);
				 ExtentReport.test.pass("Score:"+score);				 
				ExtentReport.test.info(responses.prettyPrint().toString());
				
			}
			
			else  {
				error=js.get("errorCode").toString();
				if(error.equalsIgnoreCase("2003")) {
					
				error=js.get("errorCode").toString();
				errorMessage=js.get("errorMessage").toString();
				ExtentReport.test.pass("expected StatusCode is :" +status_code +"got the same");
				ExtentReport.test.pass("error code when email verification has not done: "+ error);
				ExtentReport.test.pass("Error_Mesage_when_email_verification_has_not_done :"+errorMessage);				
				ExtentReport.test.log(Status.PASS,
						MarkupHelper.createLabel(Testcase + " is failed due to "+ error, ExtentColor.GREEN));
				}
				else {
					error=js.get("errorMessage").toString();
					ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to "+ error, ExtentColor.RED));
				}
			}
			

		} catch (AssertionError Ae) {

			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + Ae.getMessage(), ExtentColor.RED));
			Assert.fail(Ae.toString());

		} catch (Exception e) {
			System.out.println(e);
			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + e.getMessage(), ExtentColor.RED));
			Assert.fail(e.toString());

		}
	}

	
	// To view Score report as PDF format in the following path
	public static void View_pdf(String Testcase, String request, String statuscode, String content1) throws Throwable {
		try {

			HashMap<String, String> formParams = new HashMap<String, String>();
			formParams.put("id", ency_reportID);
			formParams.put("Authorization", "Bearer " + res_userToken);
			formParams.put("x-timeout", "10000");
			formParams.put("Content-Type", "application/json");
			Response responses;
			responses = RestAssured.given()
					.config(RestAssured.config()
							.encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("raw", ContentType.JSON)))
					.header("Authorization", "Bearer " + res_userToken)
					.queryParam("id", ency_reportID)
					.queryParam("key", res_userToken)
					.contentType("application/json")
					.headers(formParams)
					.when()
					.post("https://uat-ctosid.ctos.com.my/ctosid_new/PartnerReportView-viewPDFPartner").then().extract()
					.response();
			int code = responses.getStatusCode();
			Download download = new Download();
			byte[] pdfFile = download.downloadPdf(res_userToken, ency_reportID);
			download.downloadLocally(pdfFile);
			String file = download.extractContent(pdfFile);
			if (code == 200) {
                System.out.println(ANSI_RED +"To view the pdf file, Go to :"+ CID_constants.pdf_path + ANSI_RESET);
                ExtentReport.test.pass(ANSI_RED +"To view the pdf file, Go to :"+ CID_constants.pdf_path + ANSI_RESET);
				ExtentReport.test.info(file.toString());

			}
			

		} catch (AssertionError Ae) {

			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + Ae.getMessage(), ExtentColor.RED));
			Assert.fail(Ae.toString());

		} catch (Exception e) {
			System.out.println(e);

			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + e.getMessage(), ExtentColor.RED));
			Assert.fail(e.toString());

		}
	}
 
	//Payment Confirmation Test Flow
	public static void payment_confirmation(String Testcase, String request, String statuscode, String content1, String content2)
			throws FilloException, FileNotFoundException, IOException {
		try {

			JSONObject jobj = new JSONObject(request);			
			jobj.put("paymentRequestId", res_orederNum);
			jobj.put("customerId", loginID);
			System.out.println(jobj);
			HashMap<String, String> formParams = new HashMap<String, String>();
			//formParams.put("Authorization", "Bearer " + res_userToken);
			formParams.put("x-timeout", "10000");
			formParams.put("Content-Type", "application/json");
			Response responses;
			// System.out.println(CID_Baseclass.view_pdf+"id="+ency_reportID+"==&key="+res_userToken);
			responses = RestAssured.given()
					.config(RestAssured.config()
							.encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("raw", ContentType.JSON)))
					.header("Authorization", "Bearer " + res_userToken)
					
					.contentType("application/json")
					//.headers(formParams)
					.when().body(jobj.toString())
					.post("https://uat-ctosid.ctos.com.my/ctosid_new/partnerServices/v1/genPartnerPaymentConfirmation")
					      
					.then().extract().response();
			
			js = responses.jsonPath();
			//System.out.println(responses.asPrettyString().toString());
			res_status = js.get("statusCode").toString();
			if("SUCCESS".equalsIgnoreCase(statuscode)) {
				res_messagee = js.get("statusMessage").toString();
				//res_userToken = js.get("userToken").toString();
				//loginID = js.get("loginId").toString();
				//ency_reportID = js.get("scoreReportInfo.encryptedreportId").toString();
				ExtentReport.test.pass("Response status :"+res_status);
				ExtentReport.test.pass("Response Message :"+res_messagee);
				ExtentReport.test.info(responses.prettyPrint().toString());
				
			}
			else {
				error=js.get("errorMessage").toString();
				ExtentReport.test.log(Status.FAIL,
						MarkupHelper.createLabel(Testcase + " is failed due to "+error, ExtentColor.RED));
			}

		} catch (AssertionError Ae) {

			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + Ae.getMessage(), ExtentColor.RED));
			Assert.fail(Ae.toString());

		} catch (Exception e) {
            System.out.println(e);
			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + e.getMessage(), ExtentColor.RED));
			Assert.fail(e.toString());

		}
	}

	public static void purchase_scoreReport(String Testcase, String request, String statuscode, String content1, String content2)
			throws FilloException, FileNotFoundException, IOException, ParseException {
		try {

			HashMap<String, String> formParams = new HashMap<String, String>();
			formParams.put("x-timeout", "10000");
			formParams.put("Content-Type", "application/json");
			Response responses;
			responses = RestAssured.given()
					.config(RestAssured.config()
							.encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("raw", ContentType.JSON)))
					.header("Authorization", "Bearer " + res_userToken)

					.contentType("application/json").headers(formParams).when().body(request)
					.post("https://uat-ctosid.ctos.com.my/ctosid_new/partnerServices/v1/genPurchaseScoreReport").then()
					.extract().response();
			//int code = responses.getStatusCode();
			
			js = responses.jsonPath();
			res_status = js.get("statusCode").toString();
			if(res_status.equalsIgnoreCase(statuscode)) {
				res_messagee = js.get("statusMessage").toString();
				//res_userToken = js.get("userToken").toString();
				loginID = js.get("cid").toString();
				res_orederNum = js.get("orderNumber").toString();
				Verify_userDetails.verify_login_ID(loginID,userID );
				//ExtentReport.test.pass("NIC number: "+loginID);
				ExtentReport.test.pass("Order number of teh Payment is: "+res_orederNum);
				ExtentReport.test.info(responses.prettyPrint().toString());
				
			}

			else {
				error=js.get("errorMessage").toString();
				ExtentReport.test.log(Status.FAIL,
						MarkupHelper.createLabel(Testcase + " is failed due to "+ error, ExtentColor.RED));
			}

		} catch (AssertionError Ae) {

			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + Ae.getMessage(), ExtentColor.RED));
			Assert.fail(Ae.toString());

		} catch (Exception e) {

			ExtentReport.test.log(Status.FAIL,
					MarkupHelper.createLabel(Testcase + " is failed due to  " + e.getMessage(), ExtentColor.RED));
			Assert.fail(e.toString());

		}
	}

}
