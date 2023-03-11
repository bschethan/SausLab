package actions;


import Java.proj.base;
import pageObject.SecondPage;

public class SecondTest extends base{
	
	//select all hyperlink and verify whether we have name chethan
	public void verifyName() {
		
		SecondPage page = new SecondPage(driver);
		page.nameFind().sendKeys("BS");

	}

}
