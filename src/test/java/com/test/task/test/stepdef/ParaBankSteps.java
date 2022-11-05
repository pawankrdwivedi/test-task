package com.test.task.test.stepdef;

import com.test.task.framework.datacontext.DataContext;
import com.test.task.framework.datacontext.TestContext;
import com.test.task.framework.report.ReportHelper;
import com.test.task.framework.reader.ConfigReader;
import com.test.task.framework.utils.Utils;
import com.test.task.test.or.ParaBankOR;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;

public class ParaBankSteps extends BaseStep {

    public ParaBankSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("get user details from API for ParaBank.")
    public void getUserDetails() {
        JsonPath jsonPathEvaluator = api.getUserDetails("PB");
        getScenarioContext().setContext(DataContext.USER_1_FIRST_NAME, jsonPathEvaluator.get("results[0].name.first"));
        getScenarioContext().setContext(DataContext.USER_1_LAST_NAME, jsonPathEvaluator.get("results[0].name.last"));
        getScenarioContext().setContext(DataContext.USER_1_ADDRESS, jsonPathEvaluator.get("results[0].location.street.number").toString()
                + " " + jsonPathEvaluator.get("results[0].location.street.name"));
        getScenarioContext().setContext(DataContext.USER_1_CITY, jsonPathEvaluator.get("results[0].location.city"));
        getScenarioContext().setContext(DataContext.USER_1_STATE, jsonPathEvaluator.get("results[0].location.state"));
        getScenarioContext().setContext(DataContext.USER_1_ZIP_CODE, jsonPathEvaluator.get("results[0].location.postcode").toString());
        getScenarioContext().setContext(DataContext.USER_1_CELL, jsonPathEvaluator.get("results[0].cell"));

        getScenarioContext().setContext(DataContext.USER_2_FIRST_NAME, jsonPathEvaluator.get("results[1].name.first"));
        getScenarioContext().setContext(DataContext.USER_2_LAST_NAME, jsonPathEvaluator.get("results[1].name.last"));
        getScenarioContext().setContext(DataContext.USER_2_ADDRESS, jsonPathEvaluator.get("results[1].location.street.number").toString()
                + " " + jsonPathEvaluator.get("results[0].location.street.name"));
        getScenarioContext().setContext(DataContext.USER_2_CITY, jsonPathEvaluator.get("results[1].location.city"));
        getScenarioContext().setContext(DataContext.USER_2_STATE, jsonPathEvaluator.get("results[1].location.state"));
        getScenarioContext().setContext(DataContext.USER_2_ZIP_CODE, jsonPathEvaluator.get("results[1].location.postcode").toString());
        getScenarioContext().setContext(DataContext.USER_2_CELL, jsonPathEvaluator.get("results[1].cell"));
    }

    @When("user open Para Bank home page.")
    public void user_open_para_bank_home_page() throws Exception {
        selActions.openURL(ConfigReader.getPropertyValue("pb", "url"));
        if (selActions.isDisplayed(ParaBankOR.HEADER_PANEL)) {
            ReportHelper.PASS("Para Bank home page is displayed");
        } else {
            ReportHelper.FAIL("Para Bank home page is not displayed");
        }
    }

    @And("clicks on registration link.")
    public void loginToParaBank() throws Exception {
        selActions.click(ParaBankOR.REGISTER_LINK);
        if (selActions.getText(ParaBankOR.HEADER_TAG).toLowerCase().contains("signing up")) {
            ReportHelper.PASS("Register page is displayed");
        } else {
            ReportHelper.FAIL("Register page is not displayed");
        }
    }

    @And("enters sign up details.")
    public void enterSignUpDetails() throws Exception {
        selActions.type(ParaBankOR.FIRST_NAME, (String) getScenarioContext().getContext(DataContext.USER_1_FIRST_NAME));
        selActions.type(ParaBankOR.LAST_NAME, (String) getScenarioContext().getContext(DataContext.USER_1_LAST_NAME));
        selActions.type(ParaBankOR.ADDRESS, (String) getScenarioContext().getContext(DataContext.USER_1_ADDRESS));
        selActions.type(ParaBankOR.CITY, (String) getScenarioContext().getContext(DataContext.USER_1_CITY));
        selActions.type(ParaBankOR.STATE, (String) getScenarioContext().getContext(DataContext.USER_1_STATE));
        selActions.type(ParaBankOR.ZIP_CODE, (String) getScenarioContext().getContext(DataContext.USER_1_ZIP_CODE));
        selActions.type(ParaBankOR.PHONE_NUMBER, (String) getScenarioContext().getContext(DataContext.USER_1_CELL));
        selActions.type(ParaBankOR.SSN, Utils.generateRandomNumber(100000000, 999999999));
    }

    @Then("register successfully.")
    public void registerNewUser() throws Exception {
        String userName;
        boolean flag = true;
        int i = 0;
        do {
            userName = getScenarioContext().getContext(DataContext.USER_1_FIRST_NAME) + Utils.generateRandomNumber(10000, 99999);
            selActions.type(ParaBankOR.REG_USER_NAME, userName);
            selActions.type(ParaBankOR.REG_PASSWORD, userName);
            selActions.type(ParaBankOR.CONFIRM_PASSWORD, userName);
            selActions.click(ParaBankOR.REGISTER_BUTTON);
            if (selActions.getText(ParaBankOR.HEADER_TAG).toLowerCase().contains("welcome")) {
                ReportHelper.PASS("New User registered with UserName and Password: " + userName);
                flag = false;
            } else {
                ReportHelper.WARN("Error: User already registered: " + userName);
                i = i + 1;
                if (i > 5) {
                    ReportHelper.FAIL("Error: User already registered: " + userName);
                    break;
                }
            }
        } while (flag);
        getScenarioContext().setContext(DataContext.USER_NAME, userName);
    }

    @When("user click on Bill Pay link.")
    public void userClickOnBillPay() throws Exception {
        selActions.click(ParaBankOR.BILL_PAY_LINK);
        if (selActions.getText(ParaBankOR.HEADER_TAG).toLowerCase().contains("bill payment")) {
            ReportHelper.PASS("Bill Payment page is displayed");
        } else {
            ReportHelper.FAIL("Bill Payment page is not displayed");
        }

    }

    @And("enter payee details and click on send payment button.")
    public void enterPayeeDetailsAndSendPayment() throws Exception {
        getScenarioContext().setContext(DataContext.PAYEE_NAME, getScenarioContext().getContext(DataContext.USER_2_FIRST_NAME) +
                " " + getScenarioContext().getContext(DataContext.USER_2_LAST_NAME));
        selActions.type(ParaBankOR.PAYEE_NAME, (String) getScenarioContext().getContext(DataContext.PAYEE_NAME));
        selActions.type(ParaBankOR.PAYEE_ADDRESS, (String) getScenarioContext().getContext(DataContext.USER_2_ADDRESS));
        selActions.type(ParaBankOR.PAYEE_CITY, (String) getScenarioContext().getContext(DataContext.USER_2_CITY));
        selActions.type(ParaBankOR.PAYEE_STATE, (String) getScenarioContext().getContext(DataContext.USER_2_STATE));
        selActions.type(ParaBankOR.PAYEE_ZIP_CODE, (String) getScenarioContext().getContext(DataContext.USER_2_ZIP_CODE));
        selActions.type(ParaBankOR.PAYEE_PHONE, (String) getScenarioContext().getContext(DataContext.USER_2_CELL));
        String accountNo = Utils.generateRandomNumber(1000000000, 2139999999);
        selActions.type(ParaBankOR.PAYEE_ACCOUNT, accountNo);
        selActions.type(ParaBankOR.PAYEE_CONFIRM_ACCOUNT, accountNo);
        getScenarioContext().setContext(DataContext.PAYEE_AMOUNT, Utils.generateRandomNumber(10000, 99999));
        selActions.type(ParaBankOR.PAYEE_AMOUNT, (String) getScenarioContext().getContext(DataContext.PAYEE_AMOUNT));
        selActions.click(ParaBankOR.SEND_PAYMENT_BUTTON);
        selActions.waitTillElementIsDisplayed(By.xpath("//div[@ng-show='showResult']/h1"));
        if (selActions.getText(By.xpath("//div[@ng-show='showResult']/h1")).toLowerCase().contains("bill payment complete")) {
            ReportHelper.PASS("'Bill Payment Complete' page is displayed");
        } else {
            ReportHelper.FAIL("'Bill Payment Complete' page is not displayed");
        }
    }
    @Then("verify payment amount and sender's name.")
    public void verifyTransaction() throws Exception {
        String payeeName = selActions.getText(ParaBankOR.PAYEE_CONFIRMATION_NAME);
        String amount = selActions.getText(ParaBankOR.PAYEE_CONFIRMATION_AMOUNT);
        if (payeeName.toLowerCase().contains(((String) getScenarioContext().getContext(DataContext.PAYEE_NAME)).toLowerCase())) {
            if (amount.toLowerCase().contains((((String) getScenarioContext().getContext(DataContext.PAYEE_AMOUNT)).toLowerCase()))) {
                ReportHelper.PASS("Amount: " + amount + " payed successfully to User: " + payeeName);
            } else {
                ReportHelper.FAIL("Amount: " + amount + " not payed correctly");
            }
        } else {
            ReportHelper.FAIL("User: " + payeeName + " not matched successfully.");
        }
    }
}
