# Test Task #
Purpose of this README is to provide overview of framework(created for test task), environment required, how
to execute scenarios and view report.
### Overview: ###
Test Task  is developed using Selenium-Cucumber-java.
* WebDriverManager manages Selenium WebDriver (i.e., download, setup, and maintenance).
* Cucumber is used to for behavior-driven development.
* Courgette is used for parallel execution and cucumber report generation.
* Cucumber Pico-container for test data sharing between steps.
* Log4j for execution logs. 
### Libraries Used: ###
* Selenium
* Web Driver Manager
* Cucumber
* Rest-Assured
* Courgette
* Log4j
### Feature: ###
* BDD framework (test scenarios written in business readable language{Gherkin}).
* Test reporting using Courgette and Extent Reports.
* Parallel execution(Feature/Scenario level).
* Re-Execution of failed scenarios.
* Test data sharing between scenario steps.

### Framework Description: ###
<ul id="myUL">
    <li><span class="caret">results</span>
    <ul class="nested">
        <li><span class="caret">logs</span>
        <ul class="nested">
            <li><span class="caret"><strong>TestExecution.log</strong> - Test execution logs</span></li>
        </ul>
        </li>
        <li><span class="caret">reports</span>
        <ul class="nested">
            <li><span class="caret"><strong>courgette-extentreports</strong> - Extent report of last execution.</span></li>
            <li><span class="caret"><strong>courgette-report</strong> - Courgette report of last execution.</span></li>
        </ul>
        </li>
    </ul>
    </li>
</ul>
<ul id="myUL">
    <li><span class="caret">src</span>
        <ul class="nested">
        <li><span class="caret">test</span>
            <ul class="nested">
                <li>java</li>
                <ul class="nested">
                    <li><span class="caret">framework</span></li>
                    <ul class="nested">
                        <li><span class="caret"><strong>api</strong> - API execution library</span></li>
                        <li><span class="caret"><strong>datacontext</strong> - Test data sharing between scenario steps</span></li>
                        <li><span class="caret"><strong>log</strong> - Execution log</span></li>
                        <li><span class="caret"><strong>reader</strong> - Read properties file</span></li>
                        <li><span class="caret"><strong>report</strong> - Report management</span></li>
                        <li><span class="caret"><strong>selenium</strong> - Intialize webdriver and related selenium operations</span></li>
                        <li><span class="caret"><strong>utils</strong> - Generic utilities</span></li>
                    </ul>
                    <li><span class="caret">test</span></li>
                    <ul class="nested">
                        <li><span class="caret"><strong>or</strong> - application wise object locators</span></li>
                        <li><span class="caret"><strong>stepdef</strong> - Step definition mapping for feature file</span></li>
                        <li><span class="caret"><strong>testcases</strong> - Cucumber TestRunner</span></li>
                    </ul>
                </ul>
                <li>resources</li>
                <ul class="nested">
	                <li><span class="caret"><strong>configuration</strong> - Test-bed and application properties</span></li>
                    <li><span class="caret"><strong>features</strong> - Scenario feature files</span></li>
                    <li><span class="caret"><strong>Log4j2</strong> - logging properties</span></li>
                </ul>
            </ul>
        </li>
        </ul>
    </li>
</ul>


### Pre-requisite (IDE and Test Bed): ###
* IntelliJ IDEA 2021.2.1 (Community Edition)
* JDK 11.0.9
* Chrome browser
### Execute Test: ###
TestRunner file is available on path <b>"src\test\java\com\test\task\testcase\"</b>. Right click and choose option Run {*}Cucumber
### Check Test Report: ###
<h6>Extent Report:</h6>results\reports\courgette-extentreports\
<h6>Courgette Report:</h6>results\reports\courgette-report\

### Test Scenarios: ###
<h4>Para Bank:</h4>
<h6>Feature File:</h6> src\test\resources\features\ParaBank.feature
<h6>Step Definition File:</h6> src\test\java\com\test\task\test\stepdef\ParaBankSteps.java
<h6>Test Runner File:</h6> src\test\java\com\test\task\test\testcase\ParaBankCucumber.java
<h6>Scenario:</h6>
1. (Rest Assured)Use the user generator (https://randomuser.me/) to generate 2 users. The first one is the sender and the second one is the recipient.
2. (Selenium) Open the site of the test bank (https://parabank.parasoft.com/parabank/index.htm).
3. (Selenium) Register using the data of the First user (sender) {If username is already occupied, you have to change it (for example, add digits)}.
4. (Selenium) After successful registration, go to the Bill Pay page and transfer a random amount to the Second user (recipient).
5. (Selenium) Verify that the payment was successful and to the correct user (recipient).

<h4>Luma:</h4>
<h6>Feature File:</h6> src\test\resources\features\Luma.feature <br>
<h6>Step Definition File:</h6> src\test\java\com\test\task\test\stepdef\LumaSteps.java <br>
<h6>Test Runner File:</h6> src\test\java\com\test\task\test\testcase\LumaCucumber.java <br>
<h6>Scenario:</h6>
1. (Rest Assured)Use the user generator (https://randomuser.me/) to generate a user.
2. (Selenium) Open the site of the test store (https://magento.softwaretestingboard.com/).
3. (Selenium) Register using the data of the user.
4. (Selenium) After successful registration, go and buy any item.
5. (Selenium) Verify that the purchase was successful.
