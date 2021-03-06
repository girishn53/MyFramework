package com.girish.ddf.ordering;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.girish.ddf.TestBase;

public class ChangeOrder_PrivateLineTest extends TestBase {

	@BeforeTest
	public void initLogs() {
		initLogs(this.getClass());
	}

	@Test
	public void changeOrderPrivateLine() {

		APPLICATION_LOG.debug("before login");

		doDefaultLogin("loginUser_xpath", "loginPassword_xpath");
		wait(25000);

		try {

			boolean maintainPopUpisThere = isElementPresent("maintainPopUp_xpath");

			// System.out.println("boolean value of
			// maintainPopUpisThere"+maintainPopUpisThere);

			if (maintainPopUpisThere) {
				System.out.println("maintainenance pop up is present");

				click("goToControlCenter_xpath");

			}
		}

		catch (ElementNotVisibleException e) {
			e.printStackTrace();
			System.out.println("goToControlCenter_xpath element not found and in catch block");

		}

		finally {
			wait(10000);
			
			
			mouseOver("ordersTab_xpath", driver);
			
		

			click("changeLink_xpath");

			wait(10000);
			
			switchToFrame("_48_INSTANCE_p0O8eVSViIeR_iframe"); 

			//WebDriverWait wait=new WebDriverWait(driver, 30);
			
			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("serviceType_id"))));

			waitTillInvisible("loadImage_xpath", driver, 30);
			
			
			selectFromDropdown("serviceType_id", "Private Line");

			waitTillInvisible("loadImage_xpath", driver, 10);

			input("contactName_xpath", "Test");

			input("accountNumber_xpath", "67369192");
			input("serviceId_xpath", "test");
			waitTillInvisible("loadImage_xpath", driver, 10);
            
			
			wait(2000);

			click("calendar_xpath");

			String date = "16/09/2016";
			System.out.println("The date is " + date);

			Date currentDate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date dateToBeSelected = null;
			try {
				dateToBeSelected = formatter.parse(date);

			} catch (java.text.ParseException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			String month = new SimpleDateFormat("MMMM").format(dateToBeSelected);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateToBeSelected);
			int year = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			String month_yearExpected = month + " " + year;
			while (true) {
				String month_yearDisplayed = getText("monthAndYearText_xpath");
				if (month_yearDisplayed.equals(month_yearExpected))
					break;

				if (currentDate.after(dateToBeSelected))
					click("calBack_xpath");
				else
					click("calFront_xpath");
			}

			// driver.findElement(By.xpath("//td[text()='" + day +
			// "']")).click();

			driver.findElement(By.xpath("//*[contains(@id,'" + day + "')]")).click();

			click("submitButton_xpath");
			wait(1000);
			

			
			waitTillInvisible("loadImage_xpath", driver, 10);

			
			Assert.assertTrue(isElementPresent("addConfirmPopUp_xpath"),
					"confirm pop up is not present so order is not successful");

			
		}
	}
	
	@AfterTest
	public void closeBrowser(){
		quit();
		
	}
	
}
