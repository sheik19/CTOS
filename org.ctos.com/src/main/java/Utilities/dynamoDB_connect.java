package Utilities;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePart;
import javax.mail.search.SubjectTerm;
import javax.swing.text.html.parser.Parser;

import org.jsoup.Jsoup;

import io.cucumber.messages.types.Attachment;


public class dynamoDB_connect {
	
	public static String result;
	
	public static void check(String host,String mailstoreType,String user, String password) {
		
		try {
		   Properties properties = new Properties();
		  

	      properties.setProperty("mail.pop3.host", host);
	      properties.setProperty("mail.pop3.port", "995");
	      properties.setProperty("mail.pop3.starttls.enable", "true");
	      properties.setProperty("mail.pop3.ssl.enable", "true");
	      properties.setProperty("mail.pop3.starttls.required", "true");
	      
	     
	      Session emailSession = Session.getDefaultInstance(properties);
	      System.out.println(emailSession);
	      Store store = emailSession.getStore("pop3s");

	      store.connect(host,user,password);

	      //create the folder object and open it
	      Folder emailFolder = store.getFolder("INBOX");
	      emailFolder.open(Folder.READ_ONLY);

	      // retrieve the messages from the folder in an array and print it
	      Message[] messages = emailFolder.getMessages();
	      System.out.println("messages.length---" + messages.length);
	      

	      for (int i = 0, n = messages.length; i < n; i++) {
	         Message message = messages[i];
	         System.out.println("---------------------------------");
	         System.out.println("Email Number " + (i + 1));
	         System.out.println("Subject: " + message.getSubject());
	         System.out.println("From: " + message.getFrom()[0]);
	         
	         //MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	         
	         
	         getTextFromMimeMultipart(message);
	         String plainText= Jsoup.parse(message.getContent().toString()).text();
		    
             System.out.println(plainText);
	      }

	      //close the store and folder objects
	      emailFolder.close(true);
	      store.close();

	      } catch (NoSuchProviderException e1) {
	         e1.printStackTrace();
	      } catch (MessagingException e2) {
	         e2.printStackTrace();
	      } catch (Exception e3) {
	         e3.printStackTrace();
	      }
	   }

	   public static void main(String[] args) {

		   String host = "mail.theoptimum.tech";// change accordingly
		      String mailStoreType = "pop3";
		      String username = "test3@theoptimum.tech";// change accordingly
		      String password = "Optim@123";// change

	      check(host, mailStoreType, username, password);

	   }
	   
	   public static String getTextFromMimeMultipart(
			   Message message)  throws MessagingException, IOException{
		   result = "";
		   
		  String s  = message.getSubject();                                
	        System.out.println(message.getContentType());
	        if(message.getContent() instanceof Multipart)
	        {                                  
	            Multipart mime = (Multipart) message.getContent();

	            for (int i = 0; i < mime.getCount(); i++)
	            {
	                BodyPart part = mime.getBodyPart(i);
	                String content = part.getContent().toString();
	            }
	        } 
		   

		   if (message.isMimeType("text/plain")){
			   return message.getContent().toString();
		   }
		   else if (message.isMimeType("multipart/*")) {
			   //  String result = "";
			   MimeMultipart mimeMultipart = (MimeMultipart)message.getContent();
			   int count = mimeMultipart.getCount();
			   for (int i = 0; i < count; i ++){
				   BodyPart bodyPart = mimeMultipart.getBodyPart(i);
				   if (bodyPart.isMimeType("text/plain") || bodyPart.isMimeType("multipart/alternative")){
					   
					   Multipart mime = (Multipart) bodyPart.getContent();

			            for (int i1 = 0; i1 < mime.getCount(); i1++)
			            {
			                BodyPart part = mime.getBodyPart(i1);
			                result += part.getContent().toString();
			                System.out.println(result );
			            }
					   result = result + "\n" + bodyPart.getContent();
					   break;  //without break same text appears twice in my tests            
				   } else if (bodyPart.isMimeType("text/html")){
					   String html = (String) bodyPart.getContent();
					   result = result + "\n" + Jsoup.parse(html).text();

				   }
			   }
			   return result;
		   }
		   return  result;


		   //		    for (int i = 0; i < mimeMultipart.getCount(); i++) {
		   //		        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
		   //		        System.out.println(bodyPart.getContentType());
		   //		        if (bodyPart.isMimeType("text/plain")) {
		   //		            return result + "\n" + bodyPart.getContent(); // without return, same text appears twice in my tests
		   //		        } 
		   //		        result += parseBodyPart(bodyPart);
		   //		    }
		   //		    return result;
	   }
	   
	   private static String parseBodyPart(BodyPart bodyPart) throws MessagingException, IOException { 
		    if (bodyPart.isMimeType("text/html")) {
		        return "\n" + org.jsoup.Jsoup
		            .parse(bodyPart.getContent().toString())
		            .text();
		    } 
		    if (bodyPart.getContent() instanceof MimeMultipart){
		       // return getTextFromMimeMultipart(()bodyPart.getContent());
		    }

		    return "";
		}

	
	  
	}


