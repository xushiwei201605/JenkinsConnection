package core;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;

import utils.ReadProperties;

public class SendAttchMail {
	private String smtp;
	private String sender;
	private String reciever;
	private String code;
	private String title;
	private String body;
	
	public SendAttchMail() throws Exception{
		smtp = ReadProperties.getEmailPropertyValue("smtp");//
		System.out.println(smtp);
		sender = ReadProperties.getEmailPropertyValue("senderMail");
		reciever = ReadProperties.getEmailPropertyValue("recieverMail");
		code = ReadProperties.getEmailPropertyValue("Mail_code"); 
	}
	
	public void setTitle_body(String title,String body) {
		this.title = title;
		this.body = body;
	}
	
    public void Mail(String filename) throws Exception{
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.host",smtp);

        Session session = Session.getInstance(props);
        session.setDebug(true);



        MimeMessage message = new MimeMessage(session);

        Address fromAddress = new InternetAddress(sender);
        message.setFrom(fromAddress);

        Address toAddress = new InternetAddress(reciever);
        message.setRecipient(RecipientType.TO, toAddress);
        message.setSubject(title);
       
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);
        
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        
        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        message.saveChanges();
        Transport transport = session.getTransport("smtp");
        transport.connect(smtp,sender,code); 
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    @Test
    public void testMail(String args[]) throws Exception {
    	SendAttchMail s1 = new SendAttchMail();
		s1.setTitle_body("ceshi","ceshi1");
		s1.Mail("");
    }

}
