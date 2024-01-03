package Utilities;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Fillo_Reader {
	String Method;
	
	Fillo_Reader(String Method){
		this.Method=Method;
	}
	
	public Fillo fillo = new Fillo();
	public Connection connection;
	public String strQuery,Testcase,jsonbody,endpoint,URI;
	;
	public Recordset recordset;
	
    public Recordset  Readdata() throws FilloException {
    	try {
    	Fillo fillo = new Fillo();
    	connection= fillo.getConnection("D:\\RestAPI\\RestAPI\\JsonBody\\DataSheet1.xlsx");
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

}

