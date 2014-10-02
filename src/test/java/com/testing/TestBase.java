package com.testing;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TestBase {
	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static WebDriver dr = null;
	public static EventFiringWebDriver driver = null;
	public static Xls_Reader datatable = null;
	
	public void intialize() throws IOException
	{
		if(driver == null)
		{
			
		CONFIG = new Properties();
		FileInputStream fn = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\testing\\config.properties");
		CONFIG.load(fn);
		OR = new Properties();
		fn = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\testing\\OR.properties");
		OR.load(fn);
		if(CONFIG.get("browsername").equals("Firefox"))
		{
			FirefoxBinary binary = new FirefoxBinary(new File("C:\\Program Files\\VA\\Mozilla-Foundation_VA-Firefox_14.0.1_MU\\Mozilla Foundation VA Firefox 14.0.exe"));
			dr = new FirefoxDriver(binary,null);
		}
		else if (CONFIG.get("browsername").equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
			dr = new ChromeDriver();
		}
		else if (CONFIG.get("browsername").equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\IEDriverServer.exe");
			dr = new InternetExplorerDriver();
		}
		else
		{
			dr = new HtmlUnitDriver();
			
		}
		datatable = new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\com\\testing\\Suite1.xlsx");
		driver = new EventFiringWebDriver(dr);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		
	}
	
	public static WebElement getObject(String xpath_key)
	{
		try{
		return driver.findElement(By.xpath(OR.getProperty(xpath_key)));
	}catch(Throwable t)
	{
		// report the error
		return null;
	}
	}

}
