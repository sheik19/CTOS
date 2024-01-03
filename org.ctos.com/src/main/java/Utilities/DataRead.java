package Utilities;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

/*
 * This class is used Read the Data from the Excel Sheet using Fillo
 * Fillo is an api used to fetch data using query
 * @author Sheik
 */

public class DataRead {
	String Method;
	public static String updateQuery;
	public DataRead(String Method){
		this.Method=Method;
	}

	public Fillo fillo = new Fillo();
	public Connection connection;
	public String strQuery,Testcase,jsonbody,endpoint,URI;
	public String pathsheet=System.getProperty("user.dir");

	public Recordset recordset;

	public Recordset  Readdata() throws FilloException {
		try {
			Fillo fillo = new Fillo();
			connection= fillo.getConnection(pathsheet+"\\Digital_Data\\CID_Data_Sheet.xlsx");
			strQuery = "Select * from Sheet1 where Testcase ="+"'"+Method+"'";
			recordset = connection.executeQuery(strQuery);
			while (recordset.next()) {
				recordset.getField("Testcase");
				recordset.getField("TestData");

			}

		}  

		catch(Exception e) {
			System.out.println(e);
		}
		return recordset;

	}

	/*
	 * DFP_Readdata is used to fetch the DGF_DataSheet
	 * @author Sheik
	 */
	public Recordset  DFP_Readdata() throws FilloException {
		try {
			Fillo fillo = new Fillo();
			connection= fillo.getConnection(pathsheet+"\\Digital_Data\\DFP_Data_Sheet.xlsx");
			strQuery = "Select * from Sheet1 where Testcase ="+"'"+Method+"'";
			System.out.println(Method+" execution started");
			recordset = connection.executeQuery(strQuery);
			while (recordset.next()) {
				recordset.getField("Testcase");
				recordset.getField("TestData");

			}

		}

		catch(Exception e) {
			System.out.println(e);
		}
		return recordset;
	}
	
	public Recordset  DGF_upload(String status) throws FilloException {
		try {
			Fillo fillo = new Fillo();
			connection= fillo.getConnection(pathsheet+"\\Digital_Data\\DFP_Data_Sheet.xlsx");
			updateQuery="Update Sheet1 Set Status ='"+status+"'where Testcase='"+Method+"'";
			//strQuery = "Select * from Sheet1 where Testcase ='"+Method+"'";
			connection.executeUpdate(updateQuery);
			
			

		}

		catch(Exception e) {
			System.out.println(e);
		}
		return recordset;
	}
	
	
	
	
	
	
	public Recordset  goRest_Readdata() throws FilloException {
		try {
	    	Fillo fillo = new Fillo();
	    	connection= fillo.getConnection(pathsheet+"\\Digital_Data\\DataSheet1.xlsx");
	        strQuery = "Select * from Sheet1 where Testcase ="+"'"+Method+"'";
	        recordset = connection.executeQuery(strQuery);
	       while (recordset.next()) {
	           recordset.getField("Testcase");
	           recordset.getField("BaseURI");
	           recordset.getField("Request_body");
	           recordset.getField("API_endpoint");
	       }
	       
	    	}
	    	
	    	catch(Exception e) {
	    		System.out.println(e);
	    	}
	    	return recordset;
	    }
	
	public Recordset  DFC_Readdata() throws FilloException {
		try {
			Fillo fillo = new Fillo();
			connection= fillo.getConnection(pathsheet+"\\Digital_Data\\DFC_Data_Sheet.xlsx");
			strQuery = "Select * from Sheet1 where Testcase ="+"'"+Method+"'";
			System.out.println(Method+" execution started");
			recordset = connection.executeQuery(strQuery);
			while (recordset.next()) {
				recordset.getField("Testcase");
				recordset.getField("TestData");

			}

		}

		catch(Exception e) {
			System.out.println(e);
		}
		return recordset;
	}

	
	public Recordset  DFC_Ref_update(String refNumberf) throws FilloException {
		try {
			Fillo fillo = new Fillo();
			connection= fillo.getConnection(pathsheet+"\\Digital_Data\\DFC_Data_Sheet.xlsx");
			updateQuery="Update Sheet1 Set ReferenceNumber ='"+refNumberf+"'where Description='"+Method+"'";
			//strQuery = "Select * from Sheet1 where Testcase ='"+Method+"'";
			connection.executeUpdate(updateQuery);
			
			

		}

		catch(Exception e) {
			System.out.println(e);
		}
		return recordset;
	}
	
	public String  DFC_Read_ref_num(String ref_num) throws FilloException {
		try {
			Fillo fillo = new Fillo();
			connection= fillo.getConnection(pathsheet+"\\Digital_Data\\DFC_Data_Sheet.xlsx");
			strQuery = "Select * from Sheet1 where Testcase ="+"'"+ref_num+"'";
			System.out.println(Method+" execution started");
			recordset = connection.executeQuery(strQuery);
			while (recordset.next()) {
				ref_num=recordset.getField("ReferenceNumber").toString();
				

			}
			

		}

		catch(Exception e) {
			System.out.println(e);
		}
		return ref_num;
	}
	
	public Recordset  CID_Readdata() throws FilloException {
		try {
			Fillo fillo = new Fillo();
			connection= fillo.getConnection(pathsheet+"\\Digital_Data\\CID_Data_Sheet.xlsx");
			strQuery = "Select * from Sheet1 where Testcase ="+"'"+Method+"'";
			System.out.println(Method+" execution started");
			recordset = connection.executeQuery(strQuery);
			while (recordset.next()) {
				recordset.getField("Testcase");
				recordset.getField("TestData");

			}

		}

		catch(Exception e) {
			System.out.println(e);
		}
		return recordset;
	}
		


}

