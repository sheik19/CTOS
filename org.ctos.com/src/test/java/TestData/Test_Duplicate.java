package TestData;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import Utilities.ExtentReport;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class Test_Duplicate {

    static Map<String, Integer> csv1HeaderPosition;
    static Map<String, Integer> csv2HeaderPosition;
    static Map<String, Integer> headerCountMap = new HashMap<String, Integer>();
    public static String megeredFile;
    public static int TotalRowCount;
    public static List<String> headerList1 = new ArrayList<String>();
    public static List<String> headers1 = new ArrayList<String>();
    public static List dupuid = new ArrayList<String>();
    public static List<String> dupuid1= new ArrayList<String>();

	public static void main(String[] args) throws IOException, CsvValidationException {
		ExtentReport.initialize(TestData_constant.reports,TestData_constant.reportname,TestData_constant.DocumentTitle);
		TotalRowCount=0;
        String primaryKeyName = TestData_constant.PrimaryKey; 
        String file1Path = "C:\\Users\\Sheikumarali\\Downloads\\CSV\\mytestrun_16112023.csv";
        String file2Path = "C:\\Users\\Sheikumarali\\Downloads\\VARIABLES_SCORECARD.csv";

        Map<String, String[]> csv1Data =  readCSVFile(file1Path, primaryKeyName, 1);
        
        Map<String, String[]> csv2Data =  readCSVFile(file2Path, primaryKeyName, 2);
        
        megeredFile=TestData_constant.outputFile;
//        DataAnalysis(megeredFile);
        merge(csv1Data, csv2Data, "C:\\Users\\Sheikumarali\\Downloads\\New folder\\DuplicateNew.csv");

      
    }

    public static Map<String, String[]> readCSVFile(String filename, String primaryKeyColumn, int fileNumber) throws IOException, CsvValidationException {
        Map<String, String[]> data = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] header = reader.readNext();
            String uniqueKey = header[0];
            if(fileNumber==1) {
            	csv1HeaderPosition = new LinkedHashMap<>();
            } else if(fileNumber==2) {
            	csv2HeaderPosition = new LinkedHashMap<>();
            }
            for(int i=0;i<header.length;i++) {
            	String headerKey = header[i];
            	if(fileNumber==1) {
            		csv1HeaderPosition.put(headerKey, i);
            	} else if(fileNumber==2) {
            		csv2HeaderPosition.put(headerKey, i);
            	}
            	
            }
            
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String primaryKey = nextLine[0];
                data.put(primaryKey, nextLine);
            }
            
            
        }

        return data;
    }
    
    public static void merge(Map<String, String[]> csv1Data, Map<String, String[]> csv2Data, String outputFile) throws IOException, CsvValidationException{
    	CSVWriter writer = new CSVWriter(new FileWriter(outputFile));
    	CSVWriter writer1 = new CSVWriter(new FileWriter("C:\\Users\\Sheikumarali\\Downloads\\New folder\\Mismatch.csv"));
    	List<String> headerList = new ArrayList<String>();
    	List<String> headers = new ArrayList<String>();
    	
    	System.out.println(csv1HeaderPosition.keySet());
    	System.out.println(csv2HeaderPosition.keySet());
    	for(String column : csv1HeaderPosition.keySet()) {
			if(!column.equalsIgnoreCase("unique_key")) {
					
				
    			headerList1.add(column);	
    			headers1.add(column);
    			//System.out.println(headerList.toArray());
			}
			else {
				headerList1.add("unique_key");
				headers1.add(column);
			}
		}
    	writer1.writeNext(headerList1.toArray(new String[headerList1.size()]));
    	if(csv1HeaderPosition.size() == csv2HeaderPosition.size()) {
    		String column1="";
    		String column2="";
    		String column3="";
    		for(String column : csv1HeaderPosition.keySet()) {
    			if(!column.equalsIgnoreCase("unique_key")) {
    				column1 = column+"_X";
    				column2 = column+"_Y";
    				column3 = column+"_STATUS";	
    				
        			headerList.add(column1);	
        			headerList.add(column2);	
        			headerList.add(column3);
        			
        			headers.add(column);
        			//System.out.println(headerList.toArray());
    			}
    			else {
    				headerList.add("unique_key");
    				headers.add(column);
    			}
    		}
    		
    		
    		String[]arr = new String [headerList.size()];
    		System.out.println(headerList.toArray());
    		
    		
    		

    		writer.writeNext(headerList.toArray(new String[headerList.size()]));
    		int headerCount=0;
    		List<String> rowArray=null;
    		for(String uid: csv1Data.keySet()) {
    			rowArray = new ArrayList<String>();
				String[] csv1DataObject = csv1Data.get(uid);
				String[] csv2DataObject = csv2Data.get(uid);
				if(csv1DataObject == null || csv2DataObject == null) {
					System.out.println("skipping >>>>>>>"+uid);
					Mismatch(csv1Data,csv2Data,uid,headerList1, writer1);
					//rowArray.addAll(csv1HeaderPosition.keySet());
					continue;
				}
				TotalRowCount++;
				
    			for(int i=0;i<headers.size();i++) {	
    				
    				String header = headers.get(i);
    				int csv1Position = csv1HeaderPosition.get(header);
    				int csv2Position = csv2HeaderPosition.get(header);
    				boolean status = false;
    				String csv1RowData = csv1DataObject[csv1Position];
    				String csv2RowData = csv2DataObject[csv2Position];
    				
                    if(header.equalsIgnoreCase("unique_key")) {
                    	column1 = csv1RowData;
                    	rowArray.add(column1);
    					continue;
    				}
    				if(csv1RowData.equalsIgnoreCase(csv2RowData)) {
    					status = true;
						
    					if(headerCountMap.containsKey(header)) { 
    						headerCount = headerCountMap.get(header); 
    						headerCountMap.put(header, headerCount+1); 
    					} else { 
    						headerCountMap.put(header, 1); 
    					}

    					//ExtentReport.test.log(Status.PASS, "csv1columndata and csv2columndata file are same ");
    				}
    				column1 = csv1RowData;
    				column2 = csv2RowData;
    				column3 = Boolean.toString(status);
    				rowArray.add(column1);
    				rowArray.add(column2);
    				rowArray.add(column3);
    				
    			}
    			
if(dupuid.contains(uid)) {
					
					dupuid1.add(uid);
					
					
				}
				else {
					dupuid.add(uid);
				}
    			
    			writer.writeNext(rowArray.toArray(new String[rowArray.size()]));	
    		}
    		System.out.println(dupuid1);
    		
    		int arraySize = headers.size()*3;
			rowArray = new ArrayList<String>(arraySize);
			
			for(int i=0;i<arraySize;i++) {
				rowArray.add("");
			}
			System.out.println("rowArray size "+rowArray.size());
			for(int i=1;i<headers.size();i++) {
				String statusCount = headerCountMap.get(headers.get(i)).toString();
				double percentage = (double) Integer.parseInt(statusCount) / TotalRowCount * 100;
				Double.toString(percentage);
				rowArray.add(i*3, String.format("%.2f", percentage) );
				
    		}
			 
			writer.writeNext(rowArray.toArray(new String[rowArray.size()]));

    		writer.flush();
    		writer.close();
    		writer1.flush();
    		writer1.close();
    		ExtentReport.reports.flush();
    	} else {
    		
    		System.out.println("number of columns are not matching");
    		ExtentReport.reports.flush();
    	}
    }
    
    public static void Mismatch(Map<String, String[]> csv1Data, Map<String, String[]> csv2Data,String uid,List<String> headerList1, CSVWriter writer1 ) throws CsvValidationException, IOException {
    	
    	
    	
    	
    	List<String> rowArray=null;
    	
			rowArray = new ArrayList<String>();
			String[] csv1DataObject = csv1Data.get(uid);
			
			for(int i=0;i<headers1.size();i++) {	
				String header = headers1.get(i);
				int csv1Position = csv1HeaderPosition.get(header);
				String csv1RowData = csv1DataObject[csv1Position];
				rowArray.add(csv1RowData);
				
				
			}
			
			writer1.writeNext(rowArray.toArray(new String[rowArray.size()]));
			
    		
		
    	
    	
    }
    	


}

