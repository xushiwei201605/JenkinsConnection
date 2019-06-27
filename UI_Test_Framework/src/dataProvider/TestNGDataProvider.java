package dataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNGDataProvider {

	WebDriver driver=null;
	@BeforeClass
	public void initBrowser(){
		System.setProperty("webdriver.gecko.driver", "E:\\geckodriver.exe");
		System.setProperty("webdriver.firefox.bin","E:\\Mozilla Firefox\\firefox.exe");
		 driver = new FirefoxDriver();
	}
	@Test(dataProvider="txt",dataProviderClass=NsDataProvider.class)
	public void aaa(String u_name,String u_password) throws InterruptedException {
		driver.get("http://localhost:8032/mymovie/");
		System.out.println("打开网页");
		if(driver.getPageSource().contains("退出"))
		{
			driver.findElement(By.linkText("退出")).click();
		}
		
		driver.findElement(By.linkText("登录")).click();
		try {
		Thread.sleep(3000);
		driver.findElement(By.name("username")).sendKeys(u_name);
		driver.findElement(By.name("password")).sendKeys( u_password);
		//driver.findElement(By.xpath("//input[@type='submmit']")).click();
		driver.findElement(By.xpath("//input[@value='马上登录']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains(u_name));
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	
//	@AfterClass
//	public void quitBrowser() {
//		driver.quit();
//	}
	
	

	
	@Test(dataProvider="txt",dataProviderClass=TxtDataProvider.class)
	public void frontendLogin(String u_name,String u_pwd) throws InterruptedException {
		
		driver.get("http://localhost:8032/mymovie/");
		if(driver.getPageSource().contains("退出")) {
			driver.findElement(By.linkText("推出")).click();
			Thread.sleep(3000);
		}
		driver.findElement(By.linkText("登录")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("username")).sendKeys(u_name);
		driver.findElement(By.name("password")).sendKeys(u_pwd);
//		driver.findElement(By.xpath("//input[@type='submit'][2]")).click();
		driver.findElement(By.xpath("//input[@value='马上登录']")).click();
		Thread.sleep(6000);
		Assert.assertTrue(driver.getPageSource().contains(u_name));
		
		
		
	}
	
}
