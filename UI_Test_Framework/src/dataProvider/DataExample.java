package dataProvider;

import org.testng.annotations.Test;

public class DataExample {

//	@DataProvider(name="user")
//	public Object [][] getUsers(){
//		return new Object [][] {{"qingdao01","123456"},{"qingdao02","123456"}};
//	
//	}
	
	
	@Test(dataProvider="mysql",dataProviderClass=NsDataProvider.class)
	public void test1(String film,String petname) {
	System.out.println(film);	
	}
	
}
