package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	static String filePath = "conf/config.properties";
	static String eMail="conf/email.properties";
	static String freeMarkerListener = "conf/freeMarkerListener.properties";
	public static String getPropertyValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		fis.close();
		return prop.getProperty(key);
	}
	public static String getEmailPropertyValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(eMail);
		prop.load(fis);
		fis.close();
		return prop.getProperty(key);
	}
	public static String getFreeMarkerPropertyValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(freeMarkerListener);
		prop.load(fis);
		fis.close();
		return prop.getProperty(key);
	}
}
