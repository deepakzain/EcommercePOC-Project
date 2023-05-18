package com.ecommercepoc.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import com.ecommercepoc.actiondriver.Action;
import com.ecommercepoc.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.log4j.xml.DOMConfigurator;

public class BaseClass {
	public static Properties prop;	
	public static ThreadLocal<RemoteWebDriver> driver=new ThreadLocal<>();	
	
	@BeforeSuite(groups = {"Smoke"})
	public void loadConfig() {
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");
		try {
			prop=new Properties();
			System.out.println("super constructor invoked");
			FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"\\Configuration\\config.properties");
			prop.load(ip);
			System.out.println("driver: "+driver);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void launchApp() {
		WebDriverManager.chromedriver().setup();
		String browserName=prop.getProperty("browser");
		
		if(browserName.contains("Chrome")) {
			driver.set(new ChromeDriver());
		}else if(browserName.contains("FireFox")) {
			driver.set(new FirefoxDriver());
		}else if(browserName.contains("IE")) {
			driver.set(new InternetExplorerDriver());
		}
		Action.implicitWait(getDriver(), 10);
		Action.pageLoadTimeOut(getDriver(), 20);
		
		getDriver().get(prop.getProperty("url"));
	}
	@AfterSuite
	public void afterSuite() {
		ExtentManager.endReport();
	}
}
