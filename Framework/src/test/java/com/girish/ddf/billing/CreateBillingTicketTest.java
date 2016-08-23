package com.girish.ddf.billing;

import org.openqa.selenium.ElementNotVisibleException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.girish.ddf.TestBase;

public class CreateBillingTicketTest extends TestBase {

	@BeforeTest
	public void initLogs() {
		initLogs(this.getClass());
	}

	@Test
	public void createBillingTicket() {

		APPLICATION_LOG.debug("before login");

		doDefaultLogin("loginUser_xpath", "loginPassword_xpath");
		wait(20000);

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

			mouseOver("billingMegamenu_xpath", driver);

			click("createBillingTicketlink_xpath");
			
			waitTillInvisible("loadImage_xpath", driver, 10);

			wait(1000);
			click("billingaccountid_xpath");
			
			waitTillInvisible("loadImage_xpath", driver, 10);
			
			input("billingTicketTitle_xpath", "test123");
			
			click("issueCategory_xpath");
			
			click("callDetails_xpath");
			input("issue_xpath", "test142536");
			
			click("submitNow_xpath");
			
			
			Assert.assertTrue(isElementPresent("ticketcreated_xpath"), "Ticket is not created:failure");
			
			
			
			
			
			
		}
	}

}
