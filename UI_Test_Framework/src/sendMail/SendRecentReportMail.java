package sendMail;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import utils.ReadProperties;
import zipDocument.BuildZip;

public class SendRecentReportMail {
	@Test
	public void SendReport() throws Exception {
		// 压缩指定文件对象
        // 也可以是文件夹路径
		String fileToZip = ReadProperties.getEmailPropertyValue("fileToZip");
		// 待生成的zip包名
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH：mm：ss：SSS");
		String str=sdf.format(new Date());
		String zipName = "test"+str;
		// 待生成的zip保存路径
		String zipFilePath =ReadProperties.getEmailPropertyValue("zipFilePath") ;
		// 压缩
		BuildZip.fileToZip(fileToZip, zipFilePath, zipName);
		// 将打包文件作为附件发送邮件。
		SendAttchMail s1 = new SendAttchMail();
		s1.setTitle_body("helloworld测试报告", "详情请见附件");
		s1.Mail(zipFilePath+"\\"+zipName+".zip");

	}
}
