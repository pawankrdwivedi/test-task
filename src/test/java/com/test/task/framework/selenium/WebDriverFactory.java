package com.test.task.framework.selenium;

import com.test.task.framework.reader.ConfigReader;
import com.test.task.framework.log.FrameworkLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    public static WebDriver driver;

    public static WebDriver getDriver()
    {
        String browser= ConfigReader.getPropertyValue("testbed", "browser");
        try {
            switch (browser.toLowerCase().trim()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options=new ChromeOptions();
                    options.setHeadless(false);
                    driver = new ChromeDriver(options);
                    break;
                default:
                    driver= WebDriverManager.chromedriver().create();
            }
            driver.manage().window().maximize();
        }
        catch(Exception e)
        {
            FrameworkLogger.fatal("Unable to create "+browser.toUpperCase()+" Wedriver. "+e.getMessage());
        }
        FrameworkLogger.info(browser.toUpperCase()+" Wedriver created.");
        return driver;
    }

    public static WebDriver returnDriverInstance()
    {
        return driver;
    }
}
