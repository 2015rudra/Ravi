package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Testbase.Testbase;

public class TestUtil extends Testbase
{

	public static int PAGE_LOAD_TIMEOUT = 30;
	public static int IMPLICIT_WAIT = 15;
	public static int MICRO_WAIT = 5;
	public static int MIN_WAIT = 10;
	public static int MAX_WAIT = 30;

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}



	public static ArrayList<Object> extractExcelContentByColumnIndex(String FilepathandName ,String sheetName ,int columnIndex){
		ArrayList<Object> columndata = null;
		try {
			FileInputStream ios = null;
			try {
				ios  = new FileInputStream(FilepathandName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Iterator<Row> rowIterator = sheet.iterator();
			columndata = new ArrayList<>();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext())
				{
					Cell celldata=(Cell) cellIterator.next();
					if(row.getRowNum() > 0){ //To filter column headings
						if(celldata.getColumnIndex() == columnIndex){

							switch(celldata.getCellType())
							{
							case STRING:
								columndata.add(celldata.getStringCellValue());
								break;
							case NUMERIC:
								columndata.add(celldata.getNumericCellValue());
								break;
							case BOOLEAN:
								columndata.add(celldata.getBooleanCellValue());
								break;
							}
						}}}
				ios.close();
				// System.out.println(columndata);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columndata;
	}



	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag=true;
		}

		return flag;
	}

	public static boolean clearDownloadedFolder(String path) {
		boolean result = false;
		File directory = new File(path);
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.delete()) {
				result = true;
			}
		}
		return result;
	}


	public static boolean isFileDownloaded_Ext(String downloadPath, String ext){
		boolean flag=false;
		File dir = new File(downloadPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			flag = false;
		}

		System.out.println(files.length);
		for (int i = 1; i < files.length; i++) {
			if(files[i].getName().contains(ext)) {
				flag=true;
			}
		}
		return flag;
	}


	public static ArrayList<String> getFileNames(String downloadPath) 
	{	
		File file = new File(downloadPath);
		String[] fileList = file.list();
		ArrayList<String> names = new ArrayList<String>();
		for(String name:fileList)
		{
			//System.out.println(name);
			names.add(name);
		}
		//System.out.println(names);
		return names;

	}

	public static boolean waitForAnObject(WebDriver driver, WebElement elmt,
			Integer time) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(presenceOfElementLocated(elmt));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Function<WebDriver, WebElement> presenceOfElementLocated(	final WebElement locator)
	{
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement((By) locator);
			}
		};
	}





	public static boolean waitForAnObjectToBeAvailable(WebDriver driver,
			By element, Integer time) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
			return true;
		} catch (Exception e) {
			return false;
		}

	}


	public static boolean waitForPresenceOfAnObject(WebDriver driver,
			By element, Integer time) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean waitForAnObjectToBeVisible(WebDriver driver,
			WebElement element, Integer time) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(driver
					.findElement((By) element)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean waitForAnObjectToBeVisibleWebElement(WebDriver driver,
			WebElement element, Integer time) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement((By) element)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean waitForAnObjectToBeVisibilityLocated(WebDriver driver,	
			By element, Integer time) throws InterruptedException {	
		try {	
			WebDriverWait wait = new WebDriverWait(driver, time);	
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));	
			return true;	
		} catch (Exception e) {	
			return false;	
		}	
	}

	public static boolean waitForAnObjectToBeInVisible(WebDriver driver,
			By element, Integer time) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean isEnabled(By elmt, WebDriver driver) {
		return driver.findElement(elmt).isEnabled();
	}
	public static void waitForAnObjectToBeEnable(WebDriver driver,By element) throws InterruptedException {
		for (int i=0;i<=5;i++){
			if(!isEnabled(element, driver)){
				Thread.sleep(1000);
			}else{
				break;
			}
		}
	}



	public static void isChecked(By elmt, WebDriver driver) {
		if (driver.findElement(elmt).isSelected()) {
			driver.findElement(elmt).click();
		}
	}

	public static void waitForAjax(WebDriver driver)
			throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		if ((Boolean) executor
				.executeScript("return window.jQuery != undefined")) {
			while (!(Boolean) executor
					.executeScript("return jQuery.active == 0")) {
				Thread.sleep(1000);
			}
		}
		return;
	}


	public static boolean isDropDownEmpty(WebElement elmt) {

		Select select = new Select(elmt);
		List<WebElement> options = select.getOptions();
		if (options.isEmpty()) {
			return true;
		} else
			return false;
	}


	public static boolean selectFromDropdown(String Value, WebElement elmt) {
		try {
			Select select = new Select(elmt);
			select.selectByVisibleText(Value);
			return true;
		} catch (Exception e) {
			return false;
		}

	}


	public static boolean selectFromDropdownByValue(String value,
			WebElement elmt) throws InterruptedException {
		try {
			Select select = new Select(elmt);
			select.selectByValue(value);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

}
