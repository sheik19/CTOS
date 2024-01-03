package CID;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import com.groupdocs.comparison.common.exceptions.FileNotFoundException;
import com.groupdocs.comparison.internal.c.a.cd.internal.Exceptions.IO.IOException;
public class Download {

    public static byte[] downloadPdf(String authToken, String id) {
    	HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("id", id);
		 formParams.put("Authorization", "Bearer "+authToken);
		formParams.put("x-timeout", "10000");
		formParams.put("Content-Type", "application/json");

         byte[] pdf = given()
        		 .config(RestAssured.config()
							.encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("raw", ContentType.JSON)))
					.header("Authorization", "Bearer " + authToken)
                 .queryParam("id", id)
                 .queryParam("key", authToken)
					.contentType("application/json").headers(formParams).when()

					.post("https://uat-ctosid.ctos.com.my/ctosid_new/PartnerReportView-viewPDFPartner")
					.then()                
                    .extract()
                    .asByteArray();
                
        return pdf;

    }
    
    
    
    public static void downloadLocally(byte[] pdfFile) throws java.io.IOException {
        FileOutputStream fos=null;
        try {
                fos = new FileOutputStream(CID_constants.pdf_path);
                fos.write(pdfFile);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    
    public static String extractContent(byte[] pdf) throws Throwable {
        PDDocument document = PDDocument.load(new ByteArrayInputStream(pdf));
        try{
                return new PDFTextStripper().getText(document);
         } 
        finally {
       	 document.close();
        }
        }
    
    
}
