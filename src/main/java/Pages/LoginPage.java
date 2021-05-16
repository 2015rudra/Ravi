package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Testbase.Testbase;
import Utility.TestUtil;

public class LoginPage extends Testbase
{
	
	@FindBy(name="email")
	@CacheLookup
	WebElement username;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement passwpord;
	
	@FindBy(xpath="//div[@class='ui fluid large blue submit button']")
	@CacheLookup
	WebElement loginBtn;
	
	
	
	
	public LoginPage() 
	{
		
		PageFactory.initElements(driver, this);
	}
	
	public String getLoginPageTitle() 
	{
		return driver.getTitle();
	}
	
	
	public HomePage login(String uname, String pwd) throws InterruptedException 
	{
		TestUtil.waitForAnObjectToBeVisible(driver, username, TestUtil.MICRO_WAIT);
		username.sendKeys(uname);		
		passwpord.sendKeys(pwd);
		loginBtn.click();
		return new HomePage();
	}

}
