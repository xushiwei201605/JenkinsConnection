package testCase;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataProvider.NsDataProvider;

public class MysqlTest {
	WebDriver driver;
	
	@BeforeClass
	public void before() {
		System.setProperty("webdriver.gecko.driver","E:\\geckodriver.exe"); 
		System.setProperty("webdriver.firefox.bin", "E:\\Mozilla Firefox\\firefox.exe"); 		
		driver = new FirefoxDriver();
	}
	@Test(dataProvider = "mysql", dataProviderClass = NsDataProvider.class)
	public void testMySql(String user,String pwd) throws InterruptedException {
		driver.get("http://localhost:8032/mymovie/");
		if(driver.getPageSource().contains("退出")) {
			driver.findElement(By.linkText("退出"));
			Thread.sleep(2000);
		}
		driver.findElement(By.linkText("登录")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("username")).sendKeys(user);
		driver.findElement(By.name("password")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@value='马上登录']")).click();
	}
}

