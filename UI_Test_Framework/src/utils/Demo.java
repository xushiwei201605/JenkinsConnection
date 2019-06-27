package utils;

import java.io.IOException;

import org.testng.annotations.Test;

public class Demo {
	ReadProperties pro = new ReadProperties();
	
	@Test
	public void demo1() {
		try {
			System.out.println( pro.getPropertyValue("login.codename"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
