package DFP;

import org.testng.annotations.DataProvider;

public class DFP_data_provider {
	
	@DataProvider(name="risk_segmentation")
	public static Object[][] getDataFromDataprovider(){
		return new Object[][] {
			{ "risk_segmentation_1", "Request" ,"200","Low","PB_EB_SP"},
		   /* { "risk_segmentation_2", "Request" ,"200","High","PB_EB_SP"},
			{ "risk_segmentation_3", "Request" ,"200","High","PB_EB_SP"},
			{ "risk_segmentation_4", "Request" ,"200","High","PB_EB_SP"},
			{ "risk_segmentation_5", "Request" ,"200","High","PB_EB_SP"},
            { "risk_segmentation_6", "Request" ,"200","High","PB_EB_SP"},
            { "risk_segmentation_7", "Request" ,"200","High","PB_EB_SP"},
            { "risk_segmentation_8", "Request" ,"200","High","PB_EB_SP"},
            { "risk_segmentation_9", "Request" ,"200","High","PB_EB_SP"},
            { "risk_segmentation_10", "Request" ,"200","High","PB_EB_SP"},
            { "risk_segmentation_11", "Request" ,"200","High","PB_EB_SP"},
            { "risk_segmentation_12", "Request" ,"200","High","PB_EB_SP"}*/

		};


	}

	@DataProvider(name="Modules_combination")
	public static Object[][] module_combination(){
		return new Object[][] {
			{"Combination_of_Modules_1","Request","10","No module is selected","00000"},
			/*{"Combination_of_Modules_2","Request","300","Success","0_0_0_0_SIP"}, 
			{"Combination_of_Modules_3","Request","200","Success"},
			{"Combination_of_Modules_4","Request","200","Success"},
			{"Combination_of_Modules_5","Request","200","Success"}*/

		};


	}
	
	
	@DataProvider(name="Error_validation")
	public static Object[][] error_validation(){
		return new Object[][] {                   //
			{"Error_validation_1","Request","10","Invalid userApiKey"},
			{"Error_validation_2","Request","10","phone should not be empty for phone basic"}, 
			/*{"Error_validation_3","Request","10","email should not be empty for email basic"},
			{"Error_validation_4","Request","10","Invalid Request Parameter"},
			{"Error_validation_5","Request","10","phoneDefaultCountryCode is missing"}*/

		};


	}
}
         
	 
	
	
	
	
	
	
	
	
	
	
	


