package com.girish.ddf.logintestcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.girish.ddf.TestBase;

public class loginTest extends TestBase {

	@BeforeTest
	public void initLogs() {
		initLogs(this.getClass());
	}

	@Test
	public void logintest() {
		APPLICATION_LOG.debug("before login");
		doDefaultLogin("loginUser_xpath", "loginPassword_xpath");
		APPLICATION_LOG.debug("after login");

		boolean maintainPopUpisThere = isElementPresent("maintainPopUp_xpath");

		if (maintainPopUpisThere)
			System.out.println("maintainenance pop up is present");

		quit();

	}

}
