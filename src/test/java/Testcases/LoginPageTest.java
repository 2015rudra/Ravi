package Testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Testbase.Testbase;

public class LoginPageTest extends Testbase
{
	
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() throws IOException
	{
		super();
	}
	
	@BeforeTest
	public void setUp()
	{
		initialization();
		loginPage = new LoginPage();	
	}
	
	//@Ignore
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, "Cogmento CRM");
	}
	
	@Test(priority=2)
	public void loginTest() throws InterruptedException
	{
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}
