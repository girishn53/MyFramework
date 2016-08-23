package com.girish.ddf.ordering;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.girish.ddf.TestBase;

public class DisconnectOrderTest extends TestBase {

	@BeforeTest
	public void initLogs() {
		initLogs(this.getClass());
	}

	@Test

	public void disconnectOrderTest() {

		doDefaultLogin("loginUser_xpath", "loginPassword_xpath");
		wait(30000);

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
			System.out.println("goToControlCenter_xpath element not fould and in catch block");

		}

		finally {

			mouseOver("ordersTab_xpath", driver);

			click("disconnectLink_xpath");

			wait(1000);

			switchToFrame("_48_INSTANCE_FO1J0zGpUTsQ_iframe");

			wait(500);
			input("contactName_xpath", "test");

			input("phoneNumber_xpath", "7835862025");

			input("accountNumber_xpath", "67369192");

			waitTillInvisible("loadImage_xpath", driver, 10);

			input("servicesToBeDisconnected_xpath", "test");

			input("serviceId_xpath", "test");

			input("serviceAddress_xpath", "test");

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

			Assert.assertTrue(isElementPresent("disconnectConfirmPopUp_xpath"),
					"confirm pop up is not present so disconnect order is not successful");

			quit();

		}
	}
}