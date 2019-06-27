package testCase;

import org.testng.annotations.Test;

import appModules.AddMessage_Action;
import appModules.Login_Action;
import core.BaseTest;

public class AddMessageTest extends BaseTest{
	public AddMessageTest(String type) throws Exception {
		super("firefox");
		// TODO Auto-generated constructor stub
	}

	@Test
	public void addMessageSuccess() throws Exception {
		Login_Action.login(getDriver(), "qingdao01", "123456");
		String message="��Щʱ��������Ҫ������Щʱ��������Ҫ����.";
		AddMessage_Action.addMessage(getDriver(), message);
		
	}

}
