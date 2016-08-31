package com.girish.ddf.ordering;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.girish.ddf.TestBase;

import bsh.ParseException;

//import org.testng.asserts.SoftAssert;

public class AddOrder_PrivateLineTest extends TestBase {

	//SoftAssert sa = new SoftAssert();

	@BeforeTest
	public void initLogs() {
		initLogs(this.getClass());
	}

	@Test
	public void addOrderPrivateLine() {

		APPLICATION_LOG.debug("before login");

		doDefaultLogin("loginUser_xpath", "loginPassword_xpath");
		wait(25000);

		try {

			boolean maintainPopUpisThere = isElementPresent("maintainPopUp_xpath");

			if (maintainPopUpisThere) {
				System.out.println("maintainenance pop up is present");

				click("goToControlCenter_xpath");

			}
		}

		catch (ElementNotVisibleException e) {

			System.out.println("goToControlCenter_xpath element not fould and in catch block");

		}

		finally {
			wait(10000);
			
			mouseOver("ordersTab_xpath", driver);

			click("addLink_xpath");

			wait(15000);
			switchToFrame("_48_INSTANCE_ZSN4kDufaWYE_iframe");

			wait(2000);

			waitTillInvisible("loadImage_xpath", driver, 10);

			selectFromDropdown("serviceType_id", "Private Line");

			waitTillInvisible("loadImage_xpath", driver, 10);

			input("contactName_xpath", "Test");

			input("accountNumber_xpath", "67369192");
			click("multipleRadio_xpath");
			waitTillInvisible("loadImage_xpath", driver, 10);

			click("multipleRadio_xpath");
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
			waitTillInvisible("loadImage_xpath", driver, 10);

			try {
				Assert.assertTrue(isElementPresent("addConfirmPopUp_xpath"),
						"confirm pop up is not present so order is not successful");

			} catch (AssertionError e) {

				Assert.fail("confirm pop up is not present so order is not successful");
			}

			

		}
		}
	
		@AfterTest
		public void closeBrowser(){
			quit();
			
		}
	
	
}