package sauceTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import OR.HomePage;
import OR.loginpage;
import commonActions.Constants;
import commonActions.UIActions;
import commonActions.UIActionsImp;

public class SauceTest {

	public UIActions uiActions = new UIActionsImp();
	public WebDriver driver;
	public String resultFolder;
	public ExtentReports report;
	public ExtentTest test;
	Properties prop = new Properties();
	
	@BeforeClass
	public void logpath() {
		try {
			prop.load(new FileInputStream(Constants.PROPLocation));
			resultFolder = uiActions.createResultFolder(Constants.results);
			report = uiActions.ExtentReport(resultFolder);
			test = uiActions.ExtentTest(report, "SauceTest");
		}catch(Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.toString());
		}
	}

	@BeforeMethod
	public void testSetup() {
		try {
			driver = uiActions.driverInitilization(driver, prop.getProperty("Browser"));
			driver.get(prop.getProperty("ApplicationURL"));
			test.log(LogStatus.PASS, "Driver launched and Navigated to " + prop.getProperty("ApplicationURL"));
			test.log(LogStatus.PASS, test.addScreenCapture(uiActions.Screenshot(driver, resultFolder)));
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.toString());
		}
	}

	@Test(priority = 1)
	public void loginuser1() {
		if(uiActions.dataProvider("TC_01","Run").equalsIgnoreCase("Yes")) {
			login(uiActions.dataProvider("TC_01","UserName"),uiActions.dataProvider("TC_01","Password"));
		}else {
			throw new SkipException("TC_01 is skipped");
		}
	}
	@Test(priority =2)
	public void loginuser2() {
		if(uiActions.dataProvider("TC_02","Run").equalsIgnoreCase("Yes")) {
			login(uiActions.dataProvider("TC_02","UserName"),uiActions.dataProvider("TC_02","Password"));
		}else {
			throw new SkipException("TC_02 is skipped");
		}
	}
	@Test(priority =3)
	public void loginuser3() {
		if(uiActions.dataProvider("TC_03","Run").equalsIgnoreCase("Yes")) {
			login(uiActions.dataProvider("TC_03","UserName"),uiActions.dataProvider("TC_03","Password"));
		}else {
			throw new SkipException("TC_03 is skipped");
		}
	}
	@Test(priority =4)
	public void loginuser4() {
		if(uiActions.dataProvider("TC_04","Run").equalsIgnoreCase("Yes")) {
			login(uiActions.dataProvider("TC_04","UserName"),uiActions.dataProvider("TC_04","Password"));
		}else {
			throw new SkipException("TC_04 is skipped");
		}
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath = uiActions.Screenshot(driver,resultFolder);
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		driver.quit();
	}
	@AfterClass
	public void teardown() {
		report.endTest(test);
		report.flush();
	}
	
	
	public void login(String userName, String password) {
		try {
			uiActions.sendKeys(driver, loginpage.txt_userName, userName);
			test.log(LogStatus.PASS, "UserName entered in the Text box"+userName);
			uiActions.sendKeys(driver, loginpage.txt_password, password);
			test.log(LogStatus.PASS, "Password entered in the Text box");
			uiActions.click(driver, loginpage.btn_loginbutton);
			test.log(LogStatus.PASS, "Login button clicked");
			uiActions.wait(driver,HomePage.lbl_products,500);
			validation(HomePage.lbl_products);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validation(String lbl) {
		Assert.assertTrue(uiActions.elementDisplayed(driver, lbl));
	}

}
