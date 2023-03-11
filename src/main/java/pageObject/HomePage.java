package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Java.proj.base;

public class HomePage{
	
	public WebDriver driver;
	
	public HomePage(WebDriver driver){

		this.driver=driver;
	}	
	
	By googleClick = By.xpath("//input[@name='q']");
	
	public WebElement googleClickMethod() {
		return driver.findElement(googleClick);
	}
	
	By sendText = By.xpath("//input[@name='q']");
	public WebElement googleSendText() {
		return driver.findElement(sendText);
	}
	
	By searchBtn = By.xpath("//input[@name='btnK']");
	public WebElement googleSearchBtn() {
		return driver.findElement(searchBtn);
	}
}
