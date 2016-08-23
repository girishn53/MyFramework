package com.girish.ddf.ordering;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.girish.ddf.TestBase;

public class VerifyHomePageTitleTest extends TestBase {

	@BeforeTest
	public void initLogs() {
		initLogs(this.getClass());
	}

	@Test

	public void verifyTitle() {
		
		
		APPLICATION_LOG.debug("Before verifying title");

		doDefaultLogin("loginUserName_xpath", "loginPassword_xpath");
		click("loginButton_xpath");

		String actualTitle = getTitle();
		System.out.println(actualTitle);
		try {
			Assert.assertEquals(actualTitle, "Manage My Business Services | CenturyLink Business");
		} catch (Exception e) {
			e.printStackTrace();
		}

		quit();

	}

}
