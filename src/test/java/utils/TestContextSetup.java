package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import pageObjects.PageObjectManager;

public class TestContextSetup {
	public WebDriver driver;
	public String landingPageProductname;
	public PageObjectManager pageObjectManager;
	public TestBase testBase;
	public GenericUtils genericUils;

	public TestContextSetup() throws IOException {
		testBase=new TestBase();
		pageObjectManager = new PageObjectManager(testBase.webDriverManager());
		genericUils=new GenericUtils(testBase.webDriverManager());
	}
}
