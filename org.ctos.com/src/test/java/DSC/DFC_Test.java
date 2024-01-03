package DSC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import org.apache.hc.core5.http.ParseException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;

import Configuration.DFC_configuration;
import DFP.DFP_data_provider;
import Utilities.DataRead;
import Utilities.ExtentReport;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DFC_Test extends DFC_Runner {
	public static RequestSpecification httpRequest;
	public static String res_message,res_email,res_phone,
	res_socialIdentity,res_socialIdentityPlus,riskLevel,
	res_status,err_message,res_socialPresence_email,res_socialPresence_phone,error,refnum_str,res_ref_num,res_platform,req_ref_ID,res_ref_ID;
	public static JsonPath js;
	//public static DataRead data;
	public static int refnum,res_statuscode;
	public static Random rand = new Random();
	//public static DFC_configuration DFCconfig=new DFC_configuration(System.getProperty("user.dir")+"\\Properties\\DFC_config.properties");

	
	@SuppressWarnings("unchecked")
	@Test
	public static void upload_dataset_1(String Testcase,String request,String status) throws FilloException, FileNotFoundException, IOException, ParseException {
		try {

			//System.out.println();
			
			DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			FileReader file =new FileReader(DFC_baseclass.user_dir+"/Properties/"+request+".json");
			
			JSONParser par = new JSONParser();
			org.json.simple.JSONObject jobj = new org.json.simple.JSONObject();
			jobj = (org.json.simple.JSONObject)par.parse(file);
			refnum=rand.nextInt(1000);
			
			refnum_str="CTOSDSCSIT"+String.valueOf(refnum);
			//refnum = io.netty.util.internal.ThreadLocalRandom.current().nextInt(100000);
			//System.out.print(jobj);
			jobj.put("referenceNumber",refnum_str );
			//jobj.put("req_ref_ID","DSC0001" );
			System.out.println(jobj);
			HashMap<String, String> formParams = new HashMap<String, String>();
            
			formParams.put("x-api-key", DFC_baseclass.x_api_key);
			formParams.put("x-timeout", "10000");
			formParams.put("Content-Type", "application/json");
			Response responses;
            System.out.println(DFC_baseclass.env);
//System.out.println(jobj);
			responses = RestAssured.given()
					.config(RestAssured.config()
							.encoderConfig(EncoderConfig.encoderConfig()
									.encodeContentTypeAs("raw", ContentType.JSON)))
					.contentType("application/json")
					.headers(formParams)
					.when()
					.body(jobj)
					.post(DFC_baseclass.env)
					.then()
					.extract().response();
			res_statuscode=responses.getStatusCode();
             System.out.println(responses.asPrettyString());
            
			if(res_statuscode==200) {
				js = responses.jsonPath();
				res_status=js.get("responseStatus.status").toString();
				//res_message=js.get("responseStatus.message").toString();
				res_message=js.get("responseStatus.message").toString();
				res_ref_num=js.get("referenceNumber").toString();
				res_platform=js.get("platform").toString();
				res_ref_ID=js.get("refID").toString();
				ExtentReport.test.info(responses.prettyPrint());
				data = new DataRead(Testcase);
				data.DFC_Ref_update(res_ref_num);
				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("satus code :"+ res_status, ExtentColor.GREEN));
				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Response_Message :"+ res_message, ExtentColor.GREEN));
				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Response_Reference_Number :"+ res_ref_num, ExtentColor.GREEN));
				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Response_Platform :"+ res_platform, ExtentColor.GREEN));
				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Response_Platform :"+ res_ref_ID, ExtentColor.GREEN));
				System.out.println(res_message);
			}
			else if(res_statuscode==400) {
				js = responses.jsonPath();
				res_status=js.get("responseStatus.status").toString();
				
				
			}
			
			else if(res_statuscode==403) {
				js = responses.jsonPath();
				res_status=js.get("message").toString();
				
			}
			
			System.out.println(res_status);
			

}
		
		catch(Exception e) {
			System.out.println(e);
		}
}
	
	@SuppressWarnings("unchecked")
	@Test
	public static void retrieve_dataset_1(String Testcase,String ref_num,String request,String status) throws FilloException, FileNotFoundException, IOException, ParseException {
		try {

			//System.out.println();
			
			DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			FileReader file =new FileReader(DFC_baseclass.user_dir+"/Properties/retrieve.json");
			
			JSONParser par = new JSONParser();
			org.json.simple.JSONObject jobj = new org.json.simple.JSONObject();
			jobj = (org.json.simple.JSONObject)par.parse(file);
			//System.out.print(jobj);
			//jobj.put("data", DFC_baseclass.dataset);
			String r=data.DFC_Read_ref_num(ref_num).toString();
			 jobj.put("referenceNumber",r);
			System.out.println(jobj);
			HashMap<String, String> formParams = new HashMap<String, String>();
            
			formParams.put("x-api-key", DFC_baseclass.x_api_key);
			formParams.put("x-timeout", "10000");
			formParams.put("Content-Type", "application/json");
			Response responses;
System.out.println(DFC_baseclass.env);
//System.out.println(jobj);
			responses = RestAssured.given()
					.config(RestAssured.config()
							.encoderConfig(EncoderConfig.encoderConfig()
									.encodeContentTypeAs("raw", ContentType.JSON)))
					.contentType("application/json")
					.headers(formParams)
					.when()
					.body(jobj)
					.post(DFC_baseclass.retrieve)
					.then()
					.extract().response();
			res_statuscode=responses.getStatusCode();
			
			System.out.println(responses.asPrettyString());

			if(res_statuscode==200) {
				js = responses.jsonPath();
				res_status=js.get("responseStatus.status").toString();
				res_message=js.get("responseStatus.message").toString();
				res_ref_num=js.get("referenceNumber").toString();
				res_platform=js.get("platform").toString();
				res_ref_ID=js.get("refID").toString();
				ExtentReport.test.info(responses.prettyPrint().toString());
				
				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("satus code :"+ res_status, ExtentColor.GREEN));
				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Response_Message :"+ res_message, ExtentColor.GREEN));
				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Response_Reference_Number :"+ res_ref_num, ExtentColor.GREEN));
				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Response_Platform :"+ res_platform, ExtentColor.GREEN));
				ExtentReport.test.log(Status.PASS, MarkupHelper.createLabel("Response_Platform :"+ res_ref_ID, ExtentColor.GREEN));
				System.out.println(res_message);
			}
			else if(res_statuscode==400) {
				js = responses.jsonPath();
				res_status=js.get("responseStatus.status").toString();
				
			}
			
			else if(res_statuscode==403) {
				js = responses.jsonPath();
				res_status=js.get("message").toString();
				
			}
			
			System.out.println(res_status);

}
		
		catch(Exception e) {
			System.out.println(e);
		}
}
}