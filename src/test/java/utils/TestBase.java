package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {

	public WebDriver driver;

	public WebDriver webDriverManager() throws IOException {
//		FileInputStream fis = new FileInputStream(
//		System.getProperty("user.dir") + "//src//test//resources//global.properties");
//		Properties prop = new Properties();
//		prop.load(fis);
//		String url = prop.getProperty("url");
//		
//		if (driver == null) {
//			if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
//				driver = new ChromeDriver();
//				
//			} 
//			else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
//				driver = new EdgeDriver();
//				
//			}
//			driver.manage().window().maximize();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			driver.get(url);
//		}
//		return driver;
//		
//		----------------
		Properties prop = new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//global.properties");
		prop.load(fis);
		String url = prop.getProperty("url");
		String browserName=System.getProperty("browser")!=null ?System.getProperty("browser"):prop.getProperty("browser");
		if (driver == null) {
		if(browserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); //full screen
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(url);
		}
		return driver;
	}
}
