package appModules;

import org.openqa.selenium.WebDriver;

import pageObject.AddMessagePage;

public class AddMessage_Action {
	
	public static void addMessage(WebDriver wd,String message) throws Exception {
		
		AddMessagePage ap = new AddMessagePage(wd);
		wd.get("/index.php/Detail/index/id/39");
		ap.getTxtArea().sendKeys(message);
		ap.getSubmit().click();
		Thread.sleep(3000);
		
	}

}
