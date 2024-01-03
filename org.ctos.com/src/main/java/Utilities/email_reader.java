package Utilities;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class email_reader {

	public static void main(String[] args) throws MessagingException {
		// TODO Auto-generated method stub
		
		String host = "mail-imap.yopmail.com";
		String username = "ctos001@yopmail.com";
		String password = "";

		Properties props = new Properties();
		props.setProperty("mail.imap.ssl.enable", "true");

		Session session = javax.mail.Session.getInstance(props);
		Store store = session.getStore("imap");
		store.connect(host, username, password);

		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);
		Message[] messages = inbox.getMessages();


		inbox.close(false);
		store.close();

	}

}
