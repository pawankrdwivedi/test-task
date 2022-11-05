package com.test.task.framework.selenium;

import com.test.task.framework.reader.ConfigReader;
import com.test.task.framework.log.FrameworkLogger;
import com.test.task.framework.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class SeleniumActions extends WebDriverFactory {
    static int MAX_TIME_WEB_ELEMENT = Integer.parseInt(ConfigReader.getPropertyValue("testbed", "maxTimeOutInstance"));

    public void openURL(String url) throws Exception {
        try {
            driver = getDriver();
            driver.get(url);
            FrameworkLogger.info("URL opened: " + url);
        } catch (Exception e) {
            FrameworkLogger.fatal("Unable to open URL:" + url + Utils.printErrorMessage(e.getMessage()));
            throw new Exception("Unable to open URL:" + url + " " + Utils.printErrorMessage(e.getMessage()));
        }
    }

    public WebElement findElement(By by) throws Exception {
        WebElement ele;
        try {
            ele = driver.findElement(by);
            FrameworkLogger.info("Element identified in UI: " + by);
        } catch (Exception e) {
            FrameworkLogger.fatal("Unable to identify element in UI. " + by + " " + Utils.printErrorMessage(e.getMessage()));
            throw new Exception("Unable to identify element in UI. " + by + " " + Utils.printErrorMessage(e.getMessage()));
        }
        return ele;
    }

    public void click(By by) throws Exception {
        try {
            WebElement ele = findElement(by);
            click(ele);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void click(WebElement ele) throws Exception {
        try {
            ele.click();
            FrameworkLogger.info("Element is clicked.");
        } catch (Exception e) {
            FrameworkLogger.fatal("Unable to click element. " + Utils.printErrorMessage(e.getMessage()));
            throw new Exception("Unable to click element. " + Utils.printErrorMessage(e.getMessage()));
        }
    }

    public void type(By by, String text) throws Exception {
        try {
            type(findElement(by), text);
        } catch (Exception e) {
            throw new Exception(Utils.printErrorMessage(e.getMessage()));
        }
    }

    public void type(WebElement ele, String text) throws Exception {
        try {
            ele.sendKeys(text);
            FrameworkLogger.info("'" + text + "' typed in element.");
        } catch (Exception e) {
            FrameworkLogger.fatal("Unable to type '" + text + "' in element. " + Utils.printErrorMessage(e.getMessage()));
            throw new Exception("Unable to type in element: " + ele.toString() + " " + Utils.printErrorMessage(e.getMessage()));
        }
    }

    public String getText(By by) throws Exception {
        WebElement ele;
        try {
            ele = findElement(by);
        } catch (Exception e) {
            throw new Exception(Utils.printErrorMessage(e.getMessage()));
        }
        return getText(ele);
    }

    public String getText(WebElement ele) throws Exception {
        try {
            String text = ele.getText();
            FrameworkLogger.info("Element text on UI is: " + text);
            return text;
        } catch (Exception e) {
            FrameworkLogger.fatal("Unable to get text for Element: "+ele+" "+Utils.printErrorMessage(e.getMessage()));
            throw new Exception("Unable to get text for Element: "+ele+" "+Utils.printErrorMessage(e.getMessage()));
        }
    }

    public boolean isDisplayed(By by) throws Exception {
        boolean flag = false;
        try {
            Utils.wait(1);
            List<WebElement> eleCollection = findElementCollection(by);
            if (eleCollection.size() > 0) {
                FrameworkLogger.info("Element: " + by.toString() + " is displayed on UI.");
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            FrameworkLogger.fatal("Element: " + by.toString() + " is not displayed on UI.");
            throw new Exception(Utils.printErrorMessage(e.getMessage()));
        }
    }

    public List<WebElement> findElementCollection(By by) throws Exception {
        List<WebElement> eleCollection;
        try {
            eleCollection = driver.findElements(by);
        } catch (Exception e) {
            FrameworkLogger.error(("Unable to identify element collection " + by.toString() + Utils.printErrorMessage(e.getMessage())));
            throw new Exception("Unable to identify element collection " + by + " " + Utils.printErrorMessage(e.getMessage()));
        }
        FrameworkLogger.info("Element Collection identified in UI: " + by.toString());
        return eleCollection;
    }

    public void selectInDropDown(By by, String text) throws Exception {
        Select dropDown = new Select(findElement(by));
        dropDown.selectByVisibleText(text);
    }

    public void quitBrowser() throws Exception {
        try {
            if (driver != null) {
                driver.quit();
                FrameworkLogger.info("Web Driver closed.");
            }
        } catch (Exception e) {
            FrameworkLogger.info("Unable to close Web Driver. " + Utils.printErrorMessage(e.getMessage()));
            throw new Exception(Utils.printErrorMessage(e.getMessage()));
        }
    }

    public FluentWait<WebDriver> dynamicWait() {
        FluentWait<WebDriver> fluentWait;
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(MAX_TIME_WEB_ELEMENT))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);
        return fluentWait;
    }

    public void waitTillElementIsClickable(By by) throws Exception {
        WebElement ele = null;
        if (waitTillElementIsDisplayed(by)) {
            ele = findElement(by);
        }
        dynamicWait().until(ExpectedConditions.elementToBeClickable(ele));
    }

    public boolean waitTillElementIsDisplayed(By by) throws Exception {
        dynamicWait().until(ExpectedConditions.presenceOfElementLocated(by));
        return isDisplayed(by);
    }
}