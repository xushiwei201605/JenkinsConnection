package appModules;

import org.openqa.selenium.WebDriver;

import core.WebDriverEngine;

import pageObject.LoginPage;


public class Login_Action {
	public static void login(WebDriver wd,String name,String password) throws Exception {
		
		LoginPage loginPage = new LoginPage(wd);
		loginPage.getLink().click();
		Thread.sleep(3000);
		loginPage.getUsername().sendKeys(name);
		loginPage.getPassword().sendKeys(password);
		loginPage.getSubmitBtn().click();
		Thread.sleep(3000);
	
	}

}
