package com.test.task.test.stepdef;

import com.test.task.framework.selenium.SeleniumActions;
import com.test.task.framework.selenium.WebDriverFactory;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;

public class Hooks {
    public static Scenario eachScenario;

    @BeforeAll
    public static void beforeAll(){
        File logFile=new File(System.getProperty("user.dir")+"/results/logs/TestExecution.log");
        logFile.delete();
    }
    @Before
    public void setupHooks(Scenario scenario)
    {
        Hooks.eachScenario = scenario;
    }
    @AfterStep
    public void after(Scenario scenario) {
            if (scenario.isFailed()) {
                TakesScreenshot screenshot = (TakesScreenshot) WebDriverFactory.returnDriverInstance();
                scenario.attach(screenshot.getScreenshotAs(OutputType.BYTES), "image/png", scenario.getName());
            }
        }
    @After
    public void tearDownTestBed() throws Exception {
        new SeleniumActions().quitBrowser();
    }
}

