package com.ecommercepoc.actiondriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecommercepoc.base.BaseClass;

public class Action extends BaseClass{

	public static void click(WebDriver driver, WebElement ele) {

		Actions act = new Actions(driver);
		act.moveToElement(ele).click().build().perform();

	}
	
	public static boolean findElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully Found element at");

			} else {
				System.out.println("Unable to locate element at");
			}
		}
		return flag;
	}

	public static boolean isDisplayed(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) {
				System.out.println("The element is Displayed");
			} else {
				System.out.println("The element is not Displayed");
			}
		} else {
			System.out.println("Not displayed ");
		}
		return flag;
	}

	
	public static boolean isSelected(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isSelected();
			if (flag) {
				System.out.println("The element is Selected");
			} else {
				System.out.println("The element is not Selected");
			}
		} else {
			System.out.println("Not selected ");
		}
		return flag;
	}

	
	public static boolean isEnabled(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isEnabled();
			if (flag) {
				System.out.println("The element is Enabled");
			} else {
				System.out.println("The element is not Enabled");
			}
		} else {
			System.out.println("Not Enabled ");
		}
		return flag;
	}
	
	public static boolean type(WebElement ele, String text) {
		boolean flag = false;
		try {
			flag = ele.isDisplayed();
			ele.clear();
			ele.sendKeys(text);
			// logger.info("Entered text :"+text);
			flag = true;
		} catch (Exception e) {
			System.out.println("Location Not found");
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully entered value");
			} else {
				System.out.println("Unable to enter value");
			}
		}
		return flag;
	}
	
	public static int getColumncount(WebElement row) {
		List<WebElement> columns = row.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;
	}
	
	
	public static int getRowCount(WebElement table) {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}
		
	public static String getTitle(WebDriver driver) {
		boolean flag = false;

		String text = driver.getTitle();
		if (flag) {
			System.out.println("Title of the page is: \""+text+"\"");
		}
		return text;
	}
	
	public static String getCurrentURL(WebDriver driver)  {
		boolean flag = false;

		String text = driver.getCurrentUrl();
		if (flag) {
			System.out.println("Current URL is: \""+text+"\"");
		}
		return text;
	}
	
	public static void implicitWait(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
	}
	
	public static void explicitWait(WebDriver driver, WebElement element, Duration timeOut ) {
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void pageLoadTimeOut(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
	}
	
	public static String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;
	}
	
	public static String getCurrentTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}

}
