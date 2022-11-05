Feature: Test Task - Luma Test Scenario
  #Test Scenario:-->
  #1. (Rest Assured)Use the user generator (https://randomuser.me/) to generate a user.
  #2. (Selenium/Selenide) Open the site of the test store (https://magento.softwaretestingboard.com/)
  #3. (Selenium/Selenide) Register using the data of the user
  #4. (Selenium/Selenide) After successful registration, go and buy any item
  #5. (Selenium/Selenide) Verify that the purchase was successful

  Background:
    Given get user details from API for Luma.

  @luma-task
  Scenario: Register a new user in Luma and purchase an item
    When user open Luma home page.
    And clicks on 'Create an Account' link.
    Then enters new account details and create account.
    When user search an item and add it to cart.
    And clicks on 'Proceed to Checkout' button.
    And enters shipping address and click on 'Next' button.
    Then click on 'Place Order' button and verify order is placed successfully.