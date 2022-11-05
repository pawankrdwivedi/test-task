Feature: Test Task - Para Bank Test Scenario
  #Test Scenario:-->
  #1. (Rest Assured)Use the user generator (https://randomuser.me/) to generate 2 users.
  #The first one is the sender and the second one is the recipient.
  #2. (Selenium/Selenide) Open the site of the test bank (https://parabank.parasoft.com/parabank/index.htm)
  #3. (Selenium/Selenide) Register using the data of the First user (sender)
  #a. If username is already occupied, you have to change it (for example, add digits)
  #4. (Selenium/Selenide) After successful registration, go to the Bill Pay page
  #   and transfer a random amount to the Second user (recipient)
  #5. (Selenium/Selenide) Verify that the payment was successful and to the correct user (recipient)

  Background:
    Given get user details from API for ParaBank.
  @pb-task
  Scenario: Register a new user in Para Bank and transfer fund to another user
    When user open Para Bank home page.
    And clicks on registration link.
    And enters sign up details.
    Then register successfully.
    When user click on Bill Pay link.
    And enter payee details and click on send payment button.
    Then verify payment amount and sender's name.