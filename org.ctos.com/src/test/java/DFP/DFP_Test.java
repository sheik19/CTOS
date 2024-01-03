package DFP;


import static io.restassured.RestAssured.given;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.codoid.products.exception.FilloException;

import Configuration.DFB_cofiguration;
import Configuration.DFC_configuration;
import Configuration.PropertyUtils;
import Utilities.Constants;
import Utilities.DataRead;
import Utilities.ExtentReport;
import Utilities.TestUtils;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/*
 * This class is used to contains all the Test actions to Perform
 * if you want to see the flow of the Test ,
 * you can explore from here
 * @author Sheik
 */
public class DFP_Test {
	public static RequestSpecification httpRequest;
	public static String res_messagee,res_email,res_phone,res_socialIdentity,res_socialIdentityPlus,riskLevel,res_status,err_message,res_socialPresence_email,res_socialPresence_phone,error;
	public static JsonPath js;
	public static DataRead data;
	
	@SuppressWarnings("unchecked")
	@Test(dataProvider = "risk_segmentation",enabled = false,dataProviderClass = DFP_data_provider.class)
	public static void risk_segmentation(String Testcase,String request,String statuscode,String risk,String Module) throws FilloException, FileNotFoundException, IOException, ParseException {
		try {

			//System.out.println();
			
			DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			String date_change=dtf.format(cal.getTime());
			JSONParser par = new JSONParser();
			JSONObject jobj = new JSONObject();
			jobj = (JSONObject) par.parse(request);
			jobj.put("reqDate", date_change);
			String req_email=jobj.get("email").toString();
			String req_phone="91"+jobj.get("phone").toString();
			JSONObject module=(JSONObject) jobj.get("modules");
			String req_phonebasic =module.get("include_phone_basic").toString();
			String req_emailbasic=module.get("include_email_basic").toString();
			String req_socialPresence=module.get("include_social_presence").toString();
			String req_socialIdentity=module.get("include_social_identity").toString();
			String req_SocialIdentityPlus=module.get("include_social_identity_plus").toString();

			HashMap<String, String> formParams = new HashMap<String, String>();
            
			formParams.put("x-api-key", DFP_Baseclass.x_api_key);
			formParams.put("x-timeout", "10000");
			formParams.put("Content-Type", "application/json");
			Response responses;

			responses = RestAssured.given()
					.config(RestAssured.config()
							.encoderConfig(EncoderConfig.encoderConfig()
									.encodeContentTypeAs("raw", ContentType.JSON)))
					.contentType("application/json")
					.headers(formParams)
					.when()
					.body(jobj)
					.post(DFP_Baseclass.env)
					.then()
					.extract().response();
			int code=responses.getStatusCode();

			if(code==200) {
				js = responses.jsonPath();
				res_status=js.get("query.responseStatus.status").toString();

				ExtentReport.test.info(responses.prettyPrint().toString());


				Assert.assertEquals(res_status,statuscode);
				if(res_status.equalsIgnoreCase(statuscode)){
					res_messagee=js.get("query.responseStatus.message");
					res_email=js.get("query.data.emailDetails.email");
					Assert.assertEquals(req_email,res_email);
					res_phone=js.get("query.data.phoneDetails.phone");
					Assert.assertEquals(req_phone,res_phone);
					res_socialIdentity=js.get("query.data.socialIdentity.person");
					System.out.println(res_socialIdentity); 
					res_socialIdentityPlus=js.get("query.data.socialIdentityPlus.person");
					System.out.println(res_socialIdentity);
					riskLevel=js.get("query.riskLevel");
					ExtentReport.test.info("response :"+responses.prettyPrint());
					System.out.println(riskLevel);

					Assert.assertEquals(riskLevel,risk);
				    
					DFP_Runner.data.DGF_upload("PASS");
					

					ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Request_Module "+ Module, ExtentColor.GREEN));

					ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("satus code "+ res_status, ExtentColor.GREEN));

					ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("reponse_message "+ res_messagee, ExtentColor.GREEN));

					ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Risk_Level of the user is "+ riskLevel, ExtentColor.GREEN));

					ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("email ---- "+ res_email, ExtentColor.GREEN));

					ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("phoneNumber --- "+ res_phone, ExtentColor.GREEN));

					ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("SocialIdentity --- "+ res_socialIdentity, ExtentColor.GREEN));

					ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("SocialIdentityPlus --- "+ res_socialIdentityPlus, ExtentColor.GREEN));

					ExtentReport.test.log(Status.PASS,"User_Enquiry has retrived the data's successfully");
				}


			}

		}
		catch(AssertionError Ae) {

			ExtentReport.test.log(Status.FAIL, MarkupHelper.createLabel(Testcase + " is failed due to  " + Ae.getMessage(), ExtentColor.RED));
			Assert.fail(Ae.toString());


		}
		catch(Exception e) {

			ExtentReport.test.log(Status.FAIL, MarkupHelper.createLabel(Testcase + " is failed due to  " + e.getMessage(), ExtentColor.RED));
			Assert.fail(e.toString());


		}
	}



	@SuppressWarnings("unchecked")
	@Test(dataProvider = "Modules_combination",enabled = false,dataProviderClass = DFP_data_provider.class)
	public static void Modules_Testing(String Testcase,String request,String statuscode,String message,String Module) throws FilloException, FileNotFoundException, IOException, ParseException {
		try {


			DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			String date_change=dtf.format(cal.getTime());
			JSONParser par = new JSONParser();
			JSONObject jobj = new JSONObject();
			jobj = (JSONObject) par.parse(request);
			jobj.put("reqDate", date_change);
			String req_email=jobj.get("email").toString();
			String req_phone=jobj.get("phone").toString();
			JSONObject module=(JSONObject) jobj.get("modules");
			String req_phonebasic =module.get("include_phone_basic").toString();
			String req_emailbasic=module.get("include_email_basic").toString();
			String req_socialPresence=module.get("include_social_presence").toString();
			String req_socialIdentity=module.get("include_social_identity").toString();
			String req_SocialIdentityPlus=module.get("include_social_identity_plus").toString();

			HashMap<String, String> formParams = new HashMap<String, String>();

			formParams.put("x-api-key", DFP_Baseclass.x_api_key);
			formParams.put("x-timeout", "10000");
			formParams.put("Content-Type", "application/json");
			Response responses;

			responses = RestAssured.given()
					.config(RestAssured.config()
							.encoderConfig(EncoderConfig.encoderConfig()
									.encodeContentTypeAs("raw", ContentType.JSON)))
					.contentType("application/json")
					.headers(formParams)
					.when()
					.body(jobj)
					.post(Constants.DFP_BASEURL+Constants.DFP_ENDPOINT)
					.then()
					.extract().response();

			int code=responses.getStatusCode();

			if(code==200) {

				//String response=responses.then().toString();
				js = responses.jsonPath();


				ExtentReport.test.info(responses.body().prettyPrint());
				res_status=js.get("query.responseStatus.status").toString();

				Assert.assertEquals(res_status,statuscode);
				if (Testcase.equalsIgnoreCase("To Check the response , when not passing any modules in the request")) {
					System.out.println(js.get("query.responseStatus.status").toString());
					res_messagee=js.get("query.responseStatus.message");
					err_message=js.get("query.responseStatus.error");
				}
				else if(req_phonebasic.equalsIgnoreCase("1")){
					res_phone =js.get("query.data.phoneDetails.phone");
					Assert.assertEquals(req_phone,res_phone);
					if(req_emailbasic.equalsIgnoreCase("1")) {
						res_email=js.get("query.data.emailDetails.email");
						Assert.assertEquals(req_email,res_email);

					}
					else if(req_socialIdentity.equalsIgnoreCase("1")) {
						res_socialIdentity=js.get("query.data.socialIdentity.person");
						System.out.println(res_socialIdentity);

					}
					else if(req_SocialIdentityPlus.equalsIgnoreCase("1")) {
						res_socialIdentityPlus=js.get("query.data.socialIdentityPlus.person");
						System.out.println(res_socialIdentityPlus);

					}
					else if(req_socialPresence.equalsIgnoreCase("1")) {
						res_socialPresence_email=js.get("query.data.emailDetails.socialPresence");
						System.out.println(res_socialPresence_email);
						res_socialPresence_phone=js.get("query.data.phoneDetails.socialPresence");
						System.out.println(res_socialPresence_phone);

					}

				}
				else if(req_emailbasic.equalsIgnoreCase("1")){
					res_email=js.get("query.data.emailDetails.email");
					Assert.assertEquals(req_email,res_email);
					if(req_phonebasic.equalsIgnoreCase("1")) {

						res_phone =js.get("query.data.phoneDetails.email");
						Assert.assertEquals(req_phone,res_phone);

					}
					else if(req_socialIdentity.equalsIgnoreCase("1")) {
						res_socialIdentity=js.get("query.data.socialIdentity.person");
						System.out.println(res_socialIdentity);

					}
					else if(req_SocialIdentityPlus.equalsIgnoreCase("1")) {
						res_socialIdentityPlus=js.get("query.data.socialIdentityPlus.person");
						System.out.println(res_socialIdentityPlus);

					}
					else if(req_socialPresence.equalsIgnoreCase("1")) {
						res_socialPresence_email=js.get("query.data.emailDetails.socialPresence");
						System.out.println(res_socialPresence_email);
						res_socialPresence_phone=js.get("query.data.phoneDetails.socialPresence");
						System.out.println(res_socialPresence_phone);

					}

				}
				else if(req_socialPresence.equalsIgnoreCase("1")){
					res_socialPresence_email=js.get("query.data.emailDetails.socialPresence");
					System.out.println(res_socialPresence_email);
					res_socialPresence_phone=js.get("query.data.phoneDetails.socialPresence");
					System.out.println(res_socialPresence_phone);

					if(req_emailbasic.equalsIgnoreCase("1")) {

						res_phone =js.get("query.data.phoneDetails.email");
						Assert.assertEquals(req_phone,res_phone);

					}
					else if(req_socialIdentity.equalsIgnoreCase("1")) {
						res_socialIdentity=js.get("query.data.socialIdentity.person");
						System.out.println(res_socialIdentity);

					}
					else if(req_SocialIdentityPlus.equalsIgnoreCase("1")) {
						res_socialIdentityPlus=js.get("query.data.socialIdentityPlus.person");
						System.out.println(res_socialIdentityPlus);

					}
					else if(req_email.equalsIgnoreCase("1")) {
						res_email=js.get("query.data.emailDetails.email");
						Assert.assertEquals(req_email,res_email);


					}

				}
				else if(req_socialIdentity.equalsIgnoreCase("1")){
					res_socialIdentity=js.get("query.data.socialIdentity.person");
					System.out.println(res_socialIdentity);

					if(req_phonebasic.equalsIgnoreCase("1")) {

						res_phone =js.get("query.data.phoneDetails.email");
						Assert.assertEquals(req_phone,res_phone);

					}
					else if(req_emailbasic.equalsIgnoreCase("1")) {
						res_email=js.get("query.data.emailDetails.email");
						Assert.assertEquals(req_email,res_email);

					}
					else if(req_SocialIdentityPlus.equalsIgnoreCase("1")) {
						res_socialIdentityPlus=js.get("query.data.socialIdentityPlus.person");
						System.out.println(res_socialIdentityPlus);

					}
					else if(req_socialPresence.equalsIgnoreCase("1")) {
						res_socialPresence_email=js.get("query.data.emailDetails.socialPresence");
						System.out.println(res_socialPresence_email);
						res_socialPresence_phone=js.get("query.data.phoneDetails.socialPresence");
						System.out.println(res_socialPresence_phone);

					}

				}
				else if(req_SocialIdentityPlus.equalsIgnoreCase("1")){
					res_socialIdentityPlus=js.get("query.data.socialIdentity.person");
					System.out.println(res_socialIdentityPlus);

					if(req_phonebasic.equalsIgnoreCase("1")) {

						res_phone =js.get("query.data.phoneDetails.email");
						Assert.assertEquals(req_phone,res_phone);

					}
					else if(req_emailbasic.equalsIgnoreCase("1")) {
						res_email=js.get("query.data.emailDetails.email");
						Assert.assertEquals(req_email,res_email);

					}
					else if(req_socialIdentity.equalsIgnoreCase("1")) {
						res_socialIdentity=js.get("query.data.socialIdentity.person");
						System.out.println(res_socialIdentityPlus);

					}
					else if(req_socialPresence.equalsIgnoreCase("1")) {
						res_socialPresence_email=js.get("query.data.emailDetails.socialPresence");
						System.out.println(res_socialPresence_email);
						res_socialPresence_phone=js.get("query.data.phoneDetails.socialPresence");
						System.out.println(res_socialPresence_phone);

					}

				}
				else if(res_status.equalsIgnoreCase(statuscode)){
					System.out.println(js.get("query.responseStatus.status").toString());
					res_messagee=js.get("query.responseStatus.message");
					res_email=js.get("query.data.emailDetails.email");
					Assert.assertEquals(req_email,res_email);
					res_phone =js.get("query.data.phoneDetails.email");
					Assert.assertEquals(req_phone,res_phone);
					res_socialIdentity=js.get("query.data.socialIdentity.person");
					System.out.println(res_socialIdentity); 
					res_socialIdentityPlus=js.get("query.data.socialIdentityPlus.person");
					System.out.println(res_socialIdentity);
				}

				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("satus code "+ res_status, ExtentColor.GREEN));

				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("reponse_message "+ res_messagee, ExtentColor.GREEN));

				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("email ---- "+ res_email, ExtentColor.GREEN));

				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("phoneNumber --- "+ res_phone, ExtentColor.GREEN));

				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("SocialIdentity --- "+ res_socialIdentity, ExtentColor.GREEN));

				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("SocialIdentityPlus --- "+ res_socialIdentityPlus, ExtentColor.GREEN));

				ExtentReport.test.log(Status.PASS,"Request is generated the response based on the Modules selected");


			}
		}
		catch(AssertionError Ae) {

			ExtentReport.test.log(Status.FAIL, MarkupHelper.createLabel(Module + " is failed due to  " + Ae.getMessage(), ExtentColor.RED));
			Assert.fail(Ae.toString());


		}

	}




	@SuppressWarnings("unchecked")
	@Test(dataProvider = "Error_validation",enabled = true,dataProviderClass = DFP_data_provider.class)
	public static void Error_validation(String Testcase,String request,String statuscode,String message) throws FilloException, FileNotFoundException, IOException, ParseException {
		try {


			DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			String date_change=dtf.format(cal.getTime());
			JSONParser par = new JSONParser();
			JSONObject jobj = new JSONObject();
			jobj = (JSONObject) par.parse(request);
			jobj.put("reqDate", date_change);
			String req_email=jobj.get("email").toString();
			String req_phone=jobj.get("phone").toString();
			JSONObject module=(JSONObject) jobj.get("modules");
			String req_phonebasic =module.get("include_phone_basic").toString();
			String req_emailbasic=module.get("include_email_basic").toString();
			String req_socialPresence=module.get("include_social_presence").toString();
			String req_socialIdentity=module.get("include_social_identity").toString();
			String req_SocialIdentityPlus=module.get("include_social_identity_plus").toString();

			HashMap<String, String> formParams = new HashMap<String, String>();

			formParams.put("x-api-key", DFP_Baseclass.x_api_key);

			formParams.put("x-timeout", "10000");
			formParams.put("Content-Type", "application/json");
			Response responses;

			responses = RestAssured.given()
					.config(RestAssured.config()
							.encoderConfig(EncoderConfig.encoderConfig()
									.encodeContentTypeAs("raw", ContentType.JSON)))
					.contentType("application/json")
					.headers(formParams)
					.when()
					.body(jobj)
					.post(DFP_Baseclass.env)
					.then()
					.extract().response();
			
			System.out.println(responses.asPrettyString().toString());

			js = responses.jsonPath();


			ExtentReport.test.info(responses.body().prettyPrint());
			res_status=js.get("query.responseStatus.status").toString();
if(Testcase.equalsIgnoreCase("DFB-38_TC01_When passing the request with invalid user Api Key")) {
			if(res_status=="10") {
				ExtentReport.test.info(responses.prettyPrint().toString());
				System.out.println("Statuscode: "+res_status);
				ExtentReport.test.info(res_messagee);
				ExtentReport.test.info(error);
				res_messagee=js.get("query.responseStatus.message");
				error=js.get("query.responseStatus.error");
			}
}
else if (Testcase.equalsIgnoreCase("DFB-38_TC02_When passing the request without phonenumber but selected phone_basic")) {
	if(res_status=="10") {
		ExtentReport.test.info(responses.prettyPrint().toString());
		System.out.println("Statuscode: "+res_status);
		ExtentReport.test.info(res_messagee);
		ExtentReport.test.info(error);
		res_messagee=js.get("query.responseStatus.message");
		error=js.get("query.responseStatus.error");
	}
}
	
	else if (Testcase.equalsIgnoreCase("DFB-38_TC03_When passing the request without email but selected email_basic")) {
		if(res_status=="10") {
			ExtentReport.test.info(responses.prettyPrint().toString());
			System.out.println("Statuscode: "+res_status);
			ExtentReport.test.info(res_messagee);
			ExtentReport.test.info(error);
			res_messagee=js.get("query.responseStatus.message");
			error=js.get("query.responseStatus.error");
		}
}


	else if (Testcase.equalsIgnoreCase("DFB-38_TC04_When passing the request with Invalid phone number")) {
		if(res_status=="10") {
			ExtentReport.test.info(responses.prettyPrint().toString());
			System.out.println("Statuscode: "+res_status);
			ExtentReport.test.info(res_messagee);
			ExtentReport.test.info(error);
			res_messagee=js.get("query.responseStatus.message");
			error=js.get("query.responseStatus.error");
		}
}

	else if (Testcase.equalsIgnoreCase("DFB-38_TC05_When passing the request with no default country code")) {
		if(res_status=="10") {
			ExtentReport.test.info(responses.prettyPrint().toString());
			System.out.println("Statuscode: "+res_status);
			ExtentReport.test.info(res_messagee);
			ExtentReport.test.info(error);
			res_messagee=js.get("query.responseStatus.message");
			error=js.get("query.responseStatus.error");
		}
}

            res_phone =js.get("query.data.phoneDetails.phone");
            res_email =js.get("query.data.emailDetails.email");
			
			ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("status code "+ res_status, ExtentColor.GREEN));

			ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("reponse_message "+ res_messagee, ExtentColor.GREEN));
			
			ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("error_type "+ error, ExtentColor.GREEN));

			ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("email ---- "+ res_email, ExtentColor.GREEN));

			ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("phoneNumber --- "+ res_phone, ExtentColor.GREEN));

			

			ExtentReport.test.log(Status.PASS,"Error is displaying as expected");

		
			}
		catch(Exception e) {
			
		}
	
		
		}







}
