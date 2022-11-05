package com.test.task.test.stepdef;

import com.test.task.framework.datacontext.DataContext;
import com.test.task.framework.datacontext.TestContext;
import com.test.task.framework.report.ReportHelper;
import com.test.task.framework.reader.ConfigReader;
import com.test.task.framework.utils.Utils;
import com.test.task.test.or.LumaOR;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;

public class LumaSteps extends BaseStep {

    public LumaSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("get user details from API for Luma.")
    public void getUserDetails() {
        JsonPath jsonPathEvaluator = api.getUserDetails("luma");
        getScenarioContext().setContext(DataContext.USER_1_FIRST_NAME, jsonPathEvaluator.get("results[0].name.first"));
        getScenarioContext().setContext(DataContext.USER_1_LAST_NAME, jsonPathEvaluator.get("results[0].name.last"));
        getScenarioContext().setContext(DataContext.USER_1_EMAIL, jsonPathEvaluator.get("results[0].email"));
        getScenarioContext().setContext(DataContext.USER_1_ADDRESS, jsonPathEvaluator.get("results[0].location.street.number").toString()
                + " " + jsonPathEvaluator.get("results[0].location.street.name"));
        getScenarioContext().setContext(DataContext.USER_1_CITY, jsonPathEvaluator.get("results[0].location.city"));
        getScenarioContext().setContext(DataContext.USER_1_STATE, jsonPathEvaluator.get("results[0].location.state"));
        getScenarioContext().setContext(DataContext.USER_1_ZIP_CODE, jsonPathEvaluator.get("results[0].location.postcode").toString());
        getScenarioContext().setContext(DataContext.USER_1_CELL, jsonPathEvaluator.get("results[0].cell"));

    }
    @When("user open Luma home page.")
    public void openLumaHomePage() throws Exception {
        selActions.openURL(ConfigReader.getPropertyValue("luma", "url"));
        if (selActions.isDisplayed(LumaOR.HOME_PAGE_LOGO)) {
            ReportHelper.PASS("Luma home page is displayed");
        } else {
            ReportHelper.FAIL("Luma home page is not displayed");
        }
    }
    @And("clicks on 'Create an Account' link.")
    public void clickOnCreateAccountLink() throws Exception {
        selActions.click(LumaOR.CREATE_AN_ACCOUNT_LINK);
        if (selActions.getText(LumaOR.HEADER_TAG).toLowerCase().contains("new customer account")) {
            ReportHelper.PASS("'Create New Account' page is displayed");
        } else {
            ReportHelper.FAIL("'Create New Account' page is not displayed");
        }

    }
    @Then("enters new account details and create account.")
    public void enterNewAccountDetails() throws Exception {
        selActions.type(LumaOR.FIRST_NAME, (String) getScenarioContext().getContext(DataContext.USER_1_FIRST_NAME));
        selActions.type(LumaOR.LAST_NAME, (String) getScenarioContext().getContext(DataContext.USER_1_LAST_NAME));
        selActions.type(LumaOR.EMAIL, (String) getScenarioContext().getContext(DataContext.USER_1_EMAIL));
        String password = (String) getScenarioContext().getContext(DataContext.USER_1_FIRST_NAME) + Utils.generateRandomNumber(10000, 99999);
        selActions.type(LumaOR.PASSWORD, password);
        selActions.type(LumaOR.CONFIRM_PASSWORD, password);
        selActions.click(LumaOR.CREATE_ACCOUNT_BUTTON);
        System.out.println((String) getScenarioContext().getContext(DataContext.USER_1_EMAIL));
        if (selActions.getText(LumaOR.HEADER_TAG).toLowerCase().contains("my account")) {
            ReportHelper.PASS("User " + getScenarioContext().getContext(DataContext.USER_1_EMAIL) + " registered successfully and 'My Account' page is displayed");
        } else {
            ReportHelper.FAIL("User " + getScenarioContext().getContext(DataContext.USER_1_EMAIL) + " is not registered and 'My Account' page is not displayed");
        }
    }
    @When("user search an item and add it to cart.")
    public void searchItem() throws Exception {
        String shoppingItem = "pants";
        selActions.type(LumaOR.SEARCH_ITEM, shoppingItem);
        selActions.click(By.xpath("//button[@title='Search']"));
        selActions.click(selActions.findElementCollection(LumaOR.PRODUCT_ITEM).get(1));
        Utils.wait(3);
        selActions.click(By.xpath("//div[@option-label='32']"));
        Utils.wait(2);
        selActions.click(By.xpath("//div[@option-label='Blue']"));
        Utils.wait(2);
        selActions.click(LumaOR.ADD_TO_CART_BUTTON);
        selActions.waitTillElementIsDisplayed(LumaOR.SHOPPING_CART_LINK);
        selActions.click(LumaOR.SHOPPING_CART_LINK);
        Utils.wait(3);
        if (selActions.getText(LumaOR.HEADER_TAG).toLowerCase().contains("shopping cart")) {
            ReportHelper.PASS("User " + getScenarioContext().getContext(DataContext.USER_1_EMAIL) + " registered successfully and 'My Account' page is displayed");
        } else {
            ReportHelper.FAIL("User " + getScenarioContext().getContext(DataContext.USER_1_EMAIL) + " is not registered and 'My Account' page is not displayed");
        }
    }
    @And("clicks on 'Proceed to Checkout' button.")
    public void proceedToCheckout() throws Exception {
        selActions.waitTillElementIsClickable(LumaOR.PROCEED_TO_CHECKOUT_BUTTON);
        selActions.click(LumaOR.PROCEED_TO_CHECKOUT_BUTTON);
        selActions.waitTillElementIsDisplayed(By.xpath("//div[@class='step-title']"));
        if (selActions.getText(By.xpath("//div[@class='step-title']")).toLowerCase().contains("shipping address")) {
            ReportHelper.PASS("'Shipping Address' page is displayed");
        } else {
            ReportHelper.FAIL("'Shipping Address' page is not displayed");
        }
    }
    @And("enters shipping address and click on 'Next' button.")
    public void userEnterShippingAddress() throws Exception {
        selActions.waitTillElementIsClickable(LumaOR.COMPANY);
        selActions.type(LumaOR.COMPANY, "Unlimint");
        selActions.type(LumaOR.STREET, (String) getScenarioContext().getContext(DataContext.USER_1_ADDRESS));
        selActions.type(LumaOR.CITY, (String) getScenarioContext().getContext(DataContext.USER_1_CITY));
        selActions.selectInDropDown(LumaOR.STATE,  (String) getScenarioContext().getContext(DataContext.USER_1_STATE));
        selActions.type(LumaOR.ZIP_CODE, (String) getScenarioContext().getContext(DataContext.USER_1_ZIP_CODE));
        selActions.type(LumaOR.PHONE, (String) getScenarioContext().getContext(DataContext.USER_1_CELL));
        selActions.click(By.name("ko_unique_1"));
        selActions.click(By.xpath("//button/span[text()='Next']"));
        Utils.wait(10);
        if (selActions.isDisplayed(By.xpath("//div[contains(text(),'Payment')]"))) {
            ReportHelper.PASS("'Payment Method' page is displayed");
        } else {
            ReportHelper.FAIL("'Payment Method' page is not displayed");
        }
    }
    @Then("click on 'Place Order' button and verify order is placed successfully.")
    public void placeOrderAndVerify() throws Exception {
        selActions.click(By.name("billing-address-same-as-shipping"));
        selActions.click(By.xpath("//button[@title='Place Order']"));
        Utils.wait(10);
        if (selActions.getText(LumaOR.HEADER_TAG).toLowerCase().contains("your purchase")) {
            ReportHelper.PASS("Order placed successfully");
            String orderNumber = selActions.getText(By.className("order-number"));
            ReportHelper.INFO("Order Number " + orderNumber + " is placed.");
        } else {
            ReportHelper.FAIL("Order is not placed");
        }
    }
}
