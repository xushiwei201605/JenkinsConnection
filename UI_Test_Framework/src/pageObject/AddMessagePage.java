package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ObjectMap;

public class AddMessagePage {
	public WebElement element;
	public WebDriver driver;
	ObjectMap objMap = new ObjectMap("ObjectMap/addmessage.properties");
	
	public AddMessagePage(WebDriver wd) {
		this.driver = wd;
	}

	public WebElement getTxtArea() throws Exception {
		this.element =driver.findElement(objMap.getlocator("addmessage.textarea"));
		return element;
	}
	
	public WebElement getSubmit() throws Exception {
		this.element =driver.findElement(objMap.getlocator("addmessage.submit"));
		return element;
	}
}
