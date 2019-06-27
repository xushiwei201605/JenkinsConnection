package testCase;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import core.WebTestListener;

@Listeners(WebTestListener.class)
public class TestHelloWorld {
	@Test
	public void Test() throws InterruptedException {
		System.out.println("软件测试软件测试");
	}

	@Test
	public void Test1() throws InterruptedException {
		System.out.println("test1");
	}

	@Test
	public void Test2() throws InterruptedException {
		System.out.println("test2");

	}

	@Test
	public void Test3() throws InterruptedException {
		System.out.println("test3");

	}

	@Test
	public void Test4() throws InterruptedException {
		System.out.println("test4");
	}

	@Test
	public void test() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH：mm：ss：SSS");
		String str=sdf.format(new Date());
		System.out.println("当前时间是："+str);
	}

}
