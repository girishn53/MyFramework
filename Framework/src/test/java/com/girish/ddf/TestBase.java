package com.girish.ddf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.regexp.recompile;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.girish.ddf.utility.Utilities;

public class TestBase {

	public static Properties prop;

	public WebDriver driver = null;

	// it is used to create log file for every test in logs folder
	public Logger APPLICATION_LOG = null;

	public void initLogs(Class<?> class1) {

		FileAppender appender = new FileAppender();
		// configure the appender here, with file location, etc
		appender.setFile(System.getProperty("user.dir") + "//logs//" + "//" + class1.getName() + ".log");
		appender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		appender.setAppend(false);
		appender.activateOptions();

		APPLICATION_LOG = Logger.getLogger(class1);
		APPLICATION_LOG.setLevel(Level.DEBUG);
		APPLICATION_LOG.addAppender(appender);
	}

	public void initPropertyFile() {

		if (prop == null) {
			prop = new Properties();
			String path = System.getProperty("user.dir") + "\\src\\test\\resources\\project.properties";
			FileInputStream fin;
			try {
				fin = new FileInputStream(path);
				prop.load(fin);

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

	// initialise yaml file
	public void initYamlFile() {

		Utilities.setYamlFilePath("integration_testData.yml");
	}

	// getting value from yaml file
	public String getYamlVal(String yamlMapObj) {

		return Utilities.getYamlValue(yamlMapObj);
	}

	// browser launching function

	public void openBrowser() {

		initYamlFile();

		/*
		 * System.out.println(getYamlVal("browser"));
		 * 
		 * System.out.println(getYamlVal("User.BillingAddress.Address"));
		 */

		initPropertyFile();
		if (getYamlVal("browser").equalsIgnoreCase("mozila")) {

			driver = new FirefoxDriver();

		}

		else if (getYamlVal("browser").equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriverexe"));
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public void navigate() {

		driver.get(getYamlVal("appurl"));

	}

	// input function
	public void input(String identifier, String data) {

		if (identifier.endsWith("_xpath")) {
			driver.findElement(By.xpath(prop.getProperty(identifier))).sendKeys(data);
		} else if (identifier.endsWith("_id")) {
			driver.findElement(By.id(prop.getProperty(identifier))).sendKeys(data);
		} else if (identifier.endsWith("_name"))

			driver.findElement(By.name(prop.getProperty(identifier))).sendKeys(data);

	}

	public void click(String identifier) {

		if (identifier.endsWith("_xpath"))
			driver.findElement(By.xpath(prop.getProperty(identifier))).click();
		else if (identifier.endsWith("_id"))
			driver.findElement(By.id(prop.getProperty(identifier))).click();
		else if (identifier.endsWith("_name"))
			driver.findElement(By.name(prop.getProperty(identifier))).click();

	}

	public String getTitle() {

		String title = driver.getTitle();

		return title;
	}

	public void quit() {

		if (driver != null) {
			driver.quit();
			driver = null;
		}

	}

	public boolean isElementPresent(String identifier) {
		int size = 0;
   
		if (identifier.endsWith("_xpath")){
			size = driver.findElements(By.xpath(prop.getProperty(identifier))).size();
		//System.out.println("size is"+size);
		}
		else if (identifier.endsWith("_id")) {
			size = driver.findElements(By.id(prop.getProperty(identifier))).size();
		} else if (identifier.endsWith("_name")) {
			size = driver.findElements(By.name(prop.getProperty(identifier))).size();
		}

		if (size > 0)
			return true;
		else {
			return false;
		}
}


	

	public void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void switchToFrame(String id) {

		driver.switchTo().frame(id);

	}

	public void waitTillInvisible(String identifier, WebDriver driver, Integer timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(identifier)));

	}

	public void selectFromDropdown(String identifier, String value) {

		WebElement we = driver.findElement(By.id(prop.getProperty(identifier)));

		Select s = new Select(we);
		s.selectByVisibleText(value);

	}

	// hovering mouse on web element
	public void mouseOver(String identifier, WebDriver driver) {

		WebElement we = null;

		if (identifier.endsWith("_xpath")) {
			we = driver.findElement(By.xpath(prop.getProperty(identifier)));
		} else if (identifier.endsWith("_id")) {
			we = driver.findElement(By.id(prop.getProperty(identifier)));
		} else if (identifier.endsWith("_name")) {
			we = driver.findElement(By.name(prop.getProperty(identifier)));
		}

		Actions action = new Actions(driver);
		action.moveToElement(we).build().perform();
		wait(5000);

	}

	public String getText(String identifier) {

		String text = "";
		if (identifier.endsWith("_xpath")) {
			text = driver.findElement(By.xpath(prop.getProperty(identifier))).getText();
		} else if (identifier.endsWith("_id")) {
			text = driver.findElement(By.id(prop.getProperty(identifier))).getText();
		} else if (identifier.endsWith("_name")) {
			text = driver.findElement(By.name(prop.getProperty(identifier))).getText();
		}
		return text;
	}

	/****************************
	 * Application specific
	 ***************************/

	public void doDefaultLogin(String userName, String password) {

		openBrowser();
		navigate();
		input(userName, getYamlVal("User.Credentials.defaultUsername"));

		input(password, getYamlVal("User.Credentials.defaultpassword"));
		click("loginButton_xpath");
	}

}
