package testCase;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appModules.Login_Action;
import core.BaseTest;
import dataProvider.NsDataProvider;
import utils.Log;

public class LoginTest extends BaseTest{
	public LoginTest(String type) throws Exception {
		super("firefox");
		// TODO Auto-generated constructor stub
	}
	@Test(dataProvider="txt",dataProviderClass=NsDataProvider.class)
	public void loginSuccess(String user,String pwd)  {
		String url = "http://localhost:8032/mymovie/";
		webtest.open(url);
		try {
			Login_Action.login(getDriver(),user,pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error("111");
		}
		Assert.assertTrue(ifContains("退出"));
	}
	

	@Test
	public void loginFail() throws Exception {
		String url = "http://localhost:8032/mymovie/";
		webtest.open(url);
		Login_Action.login(getDriver(),"qingdao01","qing1111");
		Assert.assertTrue(ifContains("马上登录"));
	}
}
