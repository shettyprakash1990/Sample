package testscenarios;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import pages.HomePage;
import pages.LoginPage;
import pages.EndLinkApplication;

import utilities.Util;
import utilities.ExcelData;
import utilities.Values;

public class BartelbyTestCases {

	public EndLinkApplication EndLinkApplication; 
	
	@BeforeClass
	public void before() {
		
		Util.createOutputDirectory();
		Values.extent = new ExtentReports(Values.outputDirectory+"/Results.html", true);
		
	}

		@Test
	public void TC_EL_001() {
		Util.start("EndLink","TC_EL_001","Validate Login Functionality");	
		for (String testcase : Values.testcases) {
			try {
				Values.child = Values.extent.startTest(testcase);			
				Values.testCaseDataRow = Util.returnIndex(testcase);
				
				//====================================
				EndLinkApplication = new EndLinkApplication();
				LoginPage LoginPage =  EndLinkApplication.openEndLinkApplication();
				HomePage homepage = LoginPage.loginToApplication();
				
				homepage.logout();
				//====================================
				
			}catch(Exception e) {
				Util.Failed("Exception caught"+e.getMessage());
			}finally {
				Values.parent
				.appendChild(Values.child);
				Values.currentStep = 0;
				EndLinkApplication.closeApp();
			}
		}
		Values.extent.endTest(Values.parent);
		Values.extent.flush();
	}
		
		
}
