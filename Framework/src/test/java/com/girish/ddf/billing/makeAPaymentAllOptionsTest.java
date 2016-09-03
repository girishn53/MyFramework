package com.girish.ddf.billing;

import org.openqa.selenium.ElementNotVisibleException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.girish.ddf.TestBase;

public class makeAPaymentAllOptionsTest extends TestBase {

	@BeforeTest
	public void initLogs() {
		initLogs(this.getClass());
	}
	
	
	@Test
	public void  makeAPaymentAllOptions () {

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
			//e.printStackTrace();
			System.out.println("goToControlCenter_xpath element not fould and in catch block");

		}

		finally {

			mouseOver("billingMegamenu_xpath", driver);

			click("viewBill_xpath");
			
			waitTillInvisible("loadImage_xpath", driver, 20);
			
			try{
			
			boolean paperLessPopUp=isElementPresent("goPaperLesspopup_xpath");
			
			if(paperLessPopUp)
			{
				System.out.println("paperLessPopUp is present");
				
				click("goNow_xpath");
			}
			}
			catch(ElementNotVisibleException e){
				
				e.printStackTrace();
			}
			
			
			click("makeapayment_xpath");
			
			
			
		}
	}

}
