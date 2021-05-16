package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import Testbase.Testbase;
import Utility.TestUtil;

public class HomePage extends Testbase 
{
	
	
	@FindBy(xpath="//span[@class='user-display']")
	WebElement username;
	
	@FindBy(xpath="//span[contains(text(),'Contacts')]/../../button")
	WebElement addContact;
	
	
	@FindBys
	({
			@FindBy(xpath = "//span[@class='item-text']"),
			
	})
	private List<WebElement> LHSMenus;
	
	JavascriptExecutor js = (JavascriptExecutor) driver;

	
	
	
	
	public HomePage() 
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void isUserNameDisplayed() throws InterruptedException 
	{
		TestUtil.waitForAnObjectToBeVisible(driver, username, TestUtil.MICRO_WAIT);
	}
	
	public List<WebElement> getLHSMenu() 
	{
		
		return LHSMenus;
	}
	
	
	public ArrayList<String> getLHSNames() 
	{
		List<WebElement> graphs = getLHSMenu();

		
		int lhscount = graphs.size();
		ArrayList<String> LHSNames = new ArrayList<String>();

		for (int i = 1; i<= lhscount; i++ ) 
		{

			try {
			WebElement graphText = driver.findElement(By.xpath("(//span[@class='item-text'])["+i+"]"));
			//js.executeScript("arguments[0].scrollIntoView();", graphText);
			String text = graphText.getText().trim();	
			
			LHSNames.add(text);
			}
			catch (org.openqa.selenium.StaleElementReferenceException ex) 
			{
				ex.printStackTrace();
			}

		}
		
		return LHSNames;
		
	}
	
	public void clickLHSNames() throws InterruptedException 
	{
		List<WebElement> graphs = getLHSMenu();

		
		int lhscount = graphs.size();
		ArrayList<String> LHSNames = new ArrayList<String>();

		for (int i = 1; i<= lhscount; i++ ) 
		{

			try {
			WebElement graphText = driver.findElement(By.xpath("(//div[@id='main-nav']//a)["+i+"]"));
			//js.executeScript("arguments[0].scrollIntoView();", graphText);
			graphText.click();
			Thread.sleep(3000);
			
			
			}
			catch (org.openqa.selenium.StaleElementReferenceException ex) 
			{
				ex.printStackTrace();
			}

		}
		
	}
	
	public void clickOnAddContactBtn() throws InterruptedException 
	{
		TestUtil.waitForAnObjectToBeVisible(driver, addContact, TestUtil.MICRO_WAIT);
		addContact.click();
	}
	
	

}
