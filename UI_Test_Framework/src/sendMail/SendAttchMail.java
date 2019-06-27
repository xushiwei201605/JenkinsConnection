package sendMail;

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
//	private String NGappendix;
	private String body;
	
	public SendAttchMail() throws Exception{
		//均从config.properties根据value值读取相应内容
		smtp = ReadProperties.getEmailPropertyValue("smtp");//
		sender = ReadProperties.getEmailPropertyValue("senderMail");//发送者邮箱地址
		reciever = ReadProperties.getEmailPropertyValue("recieverMail");//接收者邮箱地址
		code = ReadProperties.getEmailPropertyValue("Mail_code"); //发送者邮件授权码
//		NGappendix = ReadProperties.getEmailPropertyValue("appendix");//附件路径
	}
	
	public void setTitle_body(String title,String body) {//设置邮件的标题和正文
		this.title = title;
		this.body = body;
	}
	
    public void Mail(String filename) throws Exception{
        Properties props = new Properties();
        //服务器是否要验证用户的身份信息
        props.setProperty("mail.smtp.auth", "true");
        //指定邮件的发送服务器地址
        props.setProperty("mail.smtp.host",smtp);

        Session session = Session.getInstance(props);//得到Session
        session.setDebug(true);//代表启用debug模式，可以在控制台输出smtp协议应答的过程


        //创建一个MimeMessage格式的邮件
        MimeMessage message = new MimeMessage(session);

        //设置发送者
        Address fromAddress = new InternetAddress(sender);//邮件地址
        message.setFrom(fromAddress);//设置发送的邮件地址
        //设置接收者
        Address toAddress = new InternetAddress(reciever);//要接收邮件的邮箱
        message.setRecipient(RecipientType.TO, toAddress);//设置接收者的地址

        //设置邮件的主题
        message.setSubject(title);
       /* //设置邮件的内容
        message.setText("practice trianing");*/
       
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);
        
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        
        //附件
        messageBodyPart = new MimeBodyPart();
//        String filename = NGappendix;//附件路径
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        //保存邮件
        message.saveChanges();

        //得到发送邮件的服务器(这里用的是smtp服务器)
        Transport transport = session.getTransport("smtp");

        //发送者的账号连接到smtp服务器上  @163.com可以不写
        transport.connect(smtp,sender,code); 
        //发送信息
        transport.sendMessage(message, message.getAllRecipients());
        //关闭服务器通道
        transport.close();
    }
    @Test
    public void testMail(String args[]) throws Exception {
    	SendAttchMail s1 = new SendAttchMail();
		s1.setTitle_body("标题","正文");
		s1.Mail("");
    }

}
