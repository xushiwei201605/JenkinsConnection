package dataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class NsDataProvider {

	
	@DataProvider(name="txt")
	public  Object[][] getTxtData() throws IOException{
		return new  TxtDataProvider().getTxtUser(".\\data\\user.txt");
	}
	@DataProvider(name="teacher")
	public  Object[][] getTeacherData() throws IOException{
		return new  TxtDataProvider().getTxtUser(".\\data\\teacher.cvs");
	}
	
	@DataProvider(name="excel")
	public Object[][] getExcelDada() throws IOException{
		return new ExcelDataProvider().getTestDataByExcel("data/user.xlsx","Sheet1");
	}
	
	@DataProvider(name="mysql")
	public Object[][] getMysqlDada() throws Exception{
		return new MysqlDataProvider().getTestDataByMysql();
	}
	
}
