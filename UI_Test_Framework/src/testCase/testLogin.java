package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import core.BaseTest;

public class testLogin extends BaseTest {
	public testLogin(String type) throws Exception {
		super("firefox");
	}
	@Test
	public void testLogin() {
		driver.findElement(By.id("ls_username"));
		driver.findElement(By.id("ls_password"));
		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/form/div[1]/div[2]/p[2]/button/em"));
	}
}
