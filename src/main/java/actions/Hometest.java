package actions;
import org.testng.annotations.Test;

import Java.proj.base;
import pageObject.HomePage;
import pageObject.SecondPage;


public class Hometest extends base{
	
	@Test
	public void basePageNavigation() {

		HomePage p = new HomePage(driver);
		p.googleClickMethod().click();
		p.googleSendText().sendKeys("Chethan");
		p.googleSearchBtn().click();
		SecondTest test = new SecondTest();
		test.verifyName();
		
	}
	
}
