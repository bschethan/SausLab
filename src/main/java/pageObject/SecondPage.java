package pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SecondPage {
	
	public WebDriver driver;
	
	public SecondPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}	
	
	
	@FindBy(xpath = "//input[contains(@aria-label,'Search')]")
	WebElement element;
	
	public WebElement nameFind() {
		return element;
	}
}
