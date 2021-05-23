package com.facebook.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.facebook.genericPage.MasterPage;
import com.facebook.pages.LoginPage;

public class LoginTest {
	
	

 @Test
public void doLoginTest() throws Exception
{
	 LoginPage lp = new LoginPage();
    // lp.clickEmailOrPhone();
    // lp.enterUserName();
    // lp.clickPassword();
    // lp.enterPassword();
	 lp.enterUsername();
	 lp.enterPassword();
     lp.clickLoginButton();
}

 @AfterTest
 public static void closeLOginPage()
 {
	 MasterPage.driver.close();
 }















}
