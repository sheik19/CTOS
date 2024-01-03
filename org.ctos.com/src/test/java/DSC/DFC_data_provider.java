package DSC;

import org.testng.annotations.DataProvider;

public class DFC_data_provider {
	
	@DataProvider(name="upload_dataset")
	public static Object[][] upload_datset(){
		return new Object[][] {
		{"upload_data_1","Test","200"},
		{"upload_data_2","Test","200"},
		{"upload_data_3","Test","200"},
		{"upload_data_4","Test","200"},
		{"upload_data_5","Test","200"},
		
		};
}
	
	@DataProvider(name="retrieve_dataset")
	public static Object[][] retrieve_dat(){
		return new Object[][] {
		
		{"retrieve_data_1","upload_data_1","retrieve","200"},
		{"retrieve_data_2","upload_data_2","retrieve","200"},
		{"retrieve_data_3","upload_data_3","retrieve","200"},
		{"retrieve_data_4","upload_data_4","retrieve","200"},
		{"retrieve_data_5","upload_data_5","retrieve","200"}
		};
}
			
		

}
