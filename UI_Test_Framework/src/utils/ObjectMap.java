package utils;

import java.io.FileInputStream;

import java.io.IOException;

import java.util.Properties;

import org.openqa.selenium.By;

/*
 * 用于实现在外部配置文件中配置页面元素的定位表达式
 */
public class ObjectMap {

	Properties prop = null;

	public ObjectMap(String propFile) {
		prop = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(propFile);
			prop.load(in);
			in.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public By getlocator(String ElementNameInProp) throws Exception {
		String locator = prop.getProperty(ElementNameInProp);
		// 根据参数从属性配置文件中读取对应的配置对象
		String locatorType = locator.split(":")[0];
		String locatorValue1 = locator.split(":")[1];
		String locatorValue = new String(locatorValue1.getBytes("ISO8859-1"), "UTF-8");
		System.out.println("获取的定位类型" + locatorType + "获取的定位表达式" + locatorValue);
		if (locatorType.toLowerCase().equals("id"))
			return By.id(locatorValue);
		else if (locatorType.toLowerCase().equals("name"))
			return By.name(locatorValue);
		else if (locatorType.toLowerCase().equals("tag"))
			return By.tagName(locatorValue);
		else if (locatorType.toLowerCase().equals("class"))
			return By.className(locatorValue);
		else if (locatorType.toLowerCase().equals("css"))
			return By.cssSelector(locatorValue);
		else if (locatorType.toLowerCase().equals("link"))
			return By.linkText(locatorValue);
		else if (locatorType.toLowerCase().equals("xpath"))
			return By.xpath(locatorValue);
		else
			throw new Exception("输入的locatorType未在程序中被定义" + locatorType);
	}

	// 从数据库中读取属性值
	public String getMySqlProperties(String ElementNameInProp) throws Exception {
		String locator = prop.getProperty(ElementNameInProp);
		//
		String mysqlParaName = locator.split("=")[0];
		String mysqlParaValue1 = locator.split("=")[1];
		String mysqlParaValue = new String(mysqlParaValue1.getBytes("ISO8859-1"), "UTF-8");
		return mysqlParaValue;

	}

}
