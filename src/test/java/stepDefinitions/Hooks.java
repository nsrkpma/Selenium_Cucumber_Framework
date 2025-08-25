package stepDefinitions;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import utils.TestContextSetup;
import azure.AzureDevOpsRunner;

public class Hooks {
    TestContextSetup testContextSetup;

    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    @After
    public void AfterScenario(Scenario scenario) throws IOException {
        // Azure DevOps update
        AzureDevOpsRunner.execute(scenario);

        // Quit browser
        testContextSetup.testBase.webDriverManager().quit();
    }

    @AfterStep
    public void AddScreenshot(Scenario scenario) throws IOException {
        WebDriver driver = testContextSetup.testBase.webDriverManager();
        File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
        scenario.attach(fileContent, "image/png", "screenshot");
    }
}
