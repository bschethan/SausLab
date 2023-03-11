package Java.proj;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class base {

	public static WebDriver driver;

	@BeforeTest
	public WebDriver initializeDriver() throws IOException, InterruptedException {
		
		Properties prop = new Properties();
		FileInputStream file=new FileInputStream("C:\\Users\\User\\eclipse-workspace\\proj\\src\\test\\java\\resource\\Properties.properties");
		prop.load(file);
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		 
	switch(browser) {
		case "chrome":
		System.setProperty("webdriver.chrome.driver", "C:/Users/User/eclipse-workspace/proj/src/test/driver/chromedriver.exe");	
		driver = new ChromeDriver();
		driver.get(url);	
		break;
	}
	
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS );
	driver.manage().window().maximize();
	return driver;
	}
	

	public void closeDriver() {
		driver.close();
	}
	
	
}
