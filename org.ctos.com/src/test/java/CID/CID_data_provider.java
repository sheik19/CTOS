package CID;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.collections.Lists;

/*
 * DataProvider for the Test Cases, If we want any data for individual Test cases, 
 * we can setup the data here and calls the data setup to your Test cases
 * @author Sheik
 */

//DataProvider[all the Datas for the Test cases available here]

public class CID_data_provider {
	
	@DataProvider(name="Existing_customer")
	public static Object[][] getDataFromDataprovider(){
		return new Object[][] {

			{"Existing_customer", "Request" ,"1","loginId","Existing user"},
			{"User_Dashboard","Request","1","SUCCESS","cid"},
			{"view_PDF","Request","1","pdfPath","pdfPath"},
			{"purchase_ScoreReport","Request","1","orderNumber","cid"},
			{"payment_confirmation","Request","SUCCESS","Success","cid"}
			
		   
		};


	}

	@DataProvider(name="New_customer")
	public static Object[][] User_Dashboard(){
		return new Object[][] {
			{"New_customer", "Request" ,"1","loginId","New user created"},
			{"User_Dashboard","Request","1","SUCCESS","cid"},
			
			//{"view_PDF","Request","1","pdfPath","pdfPath"},
			//{"purchase_ScoreReport","Request","1","orderNumber","cid"},
			//{"payment_confirmation","Request","SUCCESS","Success","cid"}
			

		};


	}
	
	
	@DataProvider(name="Negative_Testing")
	public static Object[][] View_PDF(){
		return new Object[][] {  
			
			{"Negative_case_1","Request","0","Email address already exists","1010"},
			{"Negative_case_2","Request","0","Existing mobile number","1012"},
			{"Negative_case_3","Request","0","Invalid gender. Possible values are 'P' or 'L' or 'O'.","8005"},//Invalid State
			{"Negative_case_4","Request","0","Invalid State","9001"},
			{"Negative_case_5","Request","0","Contact no. cannot be more than 10 characters.","4029"},
			{"Negative_case_6","Request","0","EKYC Id max length cannot be more than 15 characters.","4034"},
			{"Negative_case_7","Request","0","Contact no. cannot be more than 10 characters.","4029"},
			{"Negative_case_8","Request","0","Invalid email","1006"},
			{"Negative_case_9","Request","0","Email required.","1005"},
			{"Negative_case_10","Request","0","Email required.","1005"},
			{"Negative_case_11","Request","0","Invalid email","1006"},
			{"Negative_case_12","Request","0","Gender Required.","1016"},
			{"Negative_case_13","Request","0","Contact Info required.","8041"},
			{"Negative_case_14","Request","0","EKYC level required.","4032"},
			{"Negative_case_15","Request","0","EKYC data required.","4030"},
			{"Negative_case_16","Request","0","Contact no. required.","4025"},
			{"Negative_case_17","Request","0","Contact type required.","4026"},
			{"Negative_case_18","Request","0","Nationality required.","8006"},
			

		};


	}
	
	@DataProvider(name="purchase_scoreReport")
	public static Object[][] purchase_report(){
		return new Object[][] {                   
			{"purchase_ScoreReport","Request","1","orderNumber"}
			

		};


	}
	
	@DataProvider(name="payment_Confirmation")
	public static Object[][] payment_confirm(){
		return new Object[][] {                   
			{"payment_confirmation","Request","SUCCESS","Success"}
			

		};


	}
	
	

}
         
	 
	
	
	
	
	
	
	
	
	
	
	


