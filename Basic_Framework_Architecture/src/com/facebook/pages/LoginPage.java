package com.facebook.pages;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.facebook.genericPage.MasterPage;

public class LoginPage extends MasterPage
{

	public LoginPage() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
 
	
	
	  public void clickEmailOrPhone() { click("Email"); }
	  
	  public void enterUserName() { sendData("Email","abc@abc.com");
	  logger.info("The username is entered"); }
	  
	  
	  public void clickPassword() { click("password"); }
	  
	  public void enterPassword() { sendData("password","1234");
	  logger.info("The Password is entered"); }
	 
	 
	
	public void enterUsername() throws Exception 
	{ 
		sendData_UN("Email");
		
		
	}
	
	public void enterPassword() throws Exception 
	{
		sendData_PWD("password");
		
		
	}
	public void clickLoginButton()
	{
		click("LoginButton");
		logger.info("The Login button is clicked");
	}
	
	
	
		
	
	
	
	
	
	
	
}
