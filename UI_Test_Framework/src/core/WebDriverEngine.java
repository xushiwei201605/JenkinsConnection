package core;


import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utils.Log;


public class WebDriverEngine {

	WebDriver driver = null;
	ElementFinder finder = null;
	Actions action  =null;

	//获得所有句柄
	public String[] getAllWindowTitles() {
	    String current = driver.getWindowHandle();

	    List<String> attributes = new ArrayList<String>();
	    for (String handle : driver.getWindowHandles()) {
	      driver.switchTo().window(handle);
	      attributes.add(driver.getTitle());
	    }

	    driver.switchTo().window(current);

	    return attributes.toArray(new String[attributes.size()]);
	}
	//构造方法
	public WebDriverEngine(WebDriver driver) {
	
		this.driver = driver;
		driver.manage().window().maximize();
		finder = new ElementFinder(driver);
		action = new Actions(driver);
	}
	//实例化一个ElementFinder类的对象
	public ElementFinder getElementFinder() {
		return finder;
	}
	//进入富文本框
	public void enterFrame(String frameID) {
		this.pause(3000);
		driver.switchTo().frame(frameID);
		Log.info("Entered iframe " + frameID);
	}
	//重写上面的进入富文本框的方法
	public void enterFrame(int frameID) {
		this.pause(3000);
		driver.switchTo().frame(frameID);
		Log.info("Entered iframe " + frameID);
	}
	//离开富文本框
	public void leaveFrame() {
		driver.switchTo().defaultContent();
		Log.info("Left the iframe");
	}
	//进入浏览器窗口
	public void open(String url) {
		try {
			driver.get(url);
			pause(3000);
		} catch (Exception e) {
			e.printStackTrace();

		}
		Log.info("Opened url " + url);
	}
	//获得打开浏览器窗口的title
	public String getTitle() {
		return driver.getTitle();
	}
	//暂停执行浏览器动作
	private void pause(int time) {
		if (time <= 0) {
			return;
		}
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//文本是否呈现在页面上
	public boolean isTextPresent(String pattern) {

		String text = driver.getPageSource();
		text = text.trim();
		if (text.contains(pattern)) {
			return true;
		}
		return false;
	}
	//清空再输入内容
	public void typeAndClear(String locator, String value) {
		WebElement element = finder.findElement(locator);
		if (element != null) {
			element.clear();
			element.sendKeys(value);

		}
	}
	//输入内容
	public void type(String locator, String value) {
		WebElement element = finder.findElement(locator);
		if (element != null) {
			element.sendKeys(value);
		}
	}
	//判断元素是否被选中
	public boolean isChecked(String locator) {
		WebElement element = finder.findElement(locator);
		return element.isSelected();
	}
	//单击
	public void click(String locator) {
		WebElement element = finder.findElement(locator);
		if (element != null) {
			element.click();
			this.pause(3000);
		}
	}
	//鼠标长按操作
	public void clickLonger(String locator) {
		WebElement element = finder.findElement(locator);
		if (element != null) {
			runJs("window.scrollTo(0," + element.getLocation().x + ")");
			element.click();
			this.pause(3000);
		}
	}
	//双击操作
	public void doubleClick(String locator) throws InterruptedException {
		WebElement element = finder.findElement(locator);
		Actions builder = new Actions(driver);
		builder.doubleClick(element).build().perform();
	}
	//判断元素是否可见
	public void isDisplayed(String locator) {
		WebElement element = finder.findElement(locator);
		if (element != null) {
			System.out.println(element.isDisplayed());
		}
	}
	//获取元素的文本
	public String getText(String locator) {

		return finder.findElement(locator).getText().trim();
	}
	//元素是否可见
	public boolean isElementPresent(String locator) {
		WebElement element = null;
		try {
			element = finder.findElement(locator);
		} catch (Exception e) {

			Log.info(e.getMessage());
		}
		if (element != null) {
			return true;
		}
		{
			return false;
		}
	}
	//获取元素的value
	public String getValue(String locator) {
		return finder.findElement(locator).getAttribute("value");
	}
	//获取当前网页的url
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	//模拟浏览器的后退操作
	public void goBack() {
		driver.navigate().back();
	}
	//模拟浏览器的前进操作
	public void goForward() {
		driver.navigate().forward();
	}
	//获取alert提醒狂的操作
	public Alert getAlert() {
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	//定位下拉框
	public Select getSelect(String locator) {
		Select inputSelect = new Select(finder.findElement(locator));
		return inputSelect;
	}
	//下拉框选择操作
	public void selectByValue(String locator, String value) {
		getSelect(locator).selectByValue(value);
		this.pause(5000);
	}

	public void selectByVisibleText(String locator, String value) {
		getSelect(locator).selectByVisibleText(value);
	}

	public void selectByIndex(String locator, int index) {
		getSelect(locator).selectByIndex(index);
	}
	//获取提醒框上面的内容
	public String getAlertText() {

		return getAlert().getText();
	}
	//模拟带你几alert提醒框的接受
	public void alertAccept() {

		getAlert().accept();
	}
	//获取网页的源代码
	public String getHtmlSource() {

		return driver.getPageSource();
	}
	//执行JS语句命令
	public void runJs(String js) {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript(js);
	}

	//鼠标悬停
	public void mouseoverElement(String locator) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(finder.findElement(locator)).perform();
	}
	//切换窗口
	public void switchWidow(int i){
	    List<String> windows = new ArrayList<String>();
	    for (String handle : driver.getWindowHandles()) {
	    
	    	windows.add(handle);
	    }
	    driver.switchTo().window(windows.get(i));
	}
	//右键
	public void rightClickMouse(String locator) throws InterruptedException {
		action.contextClick(finder.findElement(locator)).perform();
		}
	//Tab键
	public void tapClick(){
	
		action.sendKeys(Keys.TAB).perform();;
	}
	
	public void tapType(String content){
		
			action.sendKeys(content).perform();
		}
	//获取单个页面的句柄
	public void getWindow(int i){
		List<String> windows = new ArrayList<String>();
		for (String handle : driver.getWindowHandles())
		{
			windows.add(handle);
		}
		driver.switchTo().window(windows.get(i));
	}
	//判断页面是否包含某个元素
	public boolean ifContains(String content) {
		return driver.getPageSource().contains(content);
	}
	

	
	
	
}
