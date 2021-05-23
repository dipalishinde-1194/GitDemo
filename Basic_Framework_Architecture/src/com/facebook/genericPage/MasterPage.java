package com.facebook.genericPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;

//constructor
public class MasterPage {

	public static WebDriver driver;
	public Properties config;
	public Properties loc;
	public Logger logger;
	

	public MasterPage() throws Exception {
		// Configuaration properties File
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\com\\facebook\\repository\\configuration.properties");
		config = new Properties();
		config.load(ip);

		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\com\\facebook\\repository\\locators.properties");
		loc = new Properties();
		loc.load(fs);

		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\com.facebook.drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (config.getProperty("browser").equalsIgnoreCase("firefox"))

		{
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\com.facebook.drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\com.facebook.drivers\\IEdriver.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		logger = logger.getLogger("MasterPage");
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\com\\facebook\\logs\\log4j.properties");
		driver.get("https://www.facebook.com/");

	}
	
	

	public void click(String xpathkey) {
		driver.findElement(By.xpath(loc.getProperty(xpathkey))).click();
	}

	public void sendData(String xpathkey, String data) {
		driver.findElement(By.xpath(loc.getProperty(xpathkey))).sendKeys(data);
	}

	public void sendData_UN(String xpathkey) throws Exception {
		File src = new File(System.getProperty("user.dir")+"\\src\\com\\facebook\\resources\\Demo.xlsx");
		FileInputStream fs1 = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fs1);
		XSSFSheet sh = wb.getSheet("Sheet1");

		String UN = sh.getRow(0).getCell(0).getStringCellValue();

		driver.findElement(By.xpath(loc.getProperty(xpathkey))).sendKeys(UN);

	}

	public void sendData_PWD(String xpathkey) throws Exception {
		File src = new File(System.getProperty("user.dir")+"\\src\\com\\facebook\\resources\\Demo.xlsx");
		FileInputStream fs1 = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fs1);
		XSSFSheet sh = wb.getSheet("Sheet1");

		String PWD = sh.getRow(0).getCell(1).getStringCellValue();

		driver.findElement(By.xpath(loc.getProperty(xpathkey))).sendKeys(PWD);
	}

	public void captureScreenshot(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./Screenshots/" + result.getName() + "png"));
			logger.info(result.getName() + "method()screenshot captured");
		}
	}

}
