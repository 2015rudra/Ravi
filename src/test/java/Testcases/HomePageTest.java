package Testcases;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Testbase.Testbase;

public class HomePageTest extends Testbase 
{
	LoginPage loginPage;
	HomePage homePage;
	
	
	public HomePageTest() 
	{
		super();
	}
	
	@BeforeTest
	public void setUp() throws InterruptedException 
	{
		initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Ignore
	@Test(priority=1)
	public void isUserNameDisplayedTest() throws InterruptedException 
	{
		homePage.isUserNameDisplayed();
		Thread.sleep(10000);
	}
	
	@Ignore
	@Test(priority=2)
	public void LHSNameTest() throws InterruptedException 
	{
		ArrayList<String> expec = new ArrayList<String>(Arrays.asList("Home","Calendar", "Contacts","Companies", "Deals", "Tasks","Cases", "Calls", "Documents", "Email", "Campaigns", "Forms"));
		ArrayList<String> actu = homePage.getLHSNames();
		Assert.assertEquals(expec, actu);
	}
	
	
	@Test(priority=2)
	public void clickLHSNameTest() throws InterruptedException 
	{
		homePage.clickLHSNames();
		
	}
	
	
	@AfterTest
	public void tearDown() 
	{
		driver.quit();
	}
	
	
}
