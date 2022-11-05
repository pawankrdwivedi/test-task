package com.test.task.test.or;

import org.openqa.selenium.By;

public class LumaOR {

    /**
     * HOME PAGE
     */
    public static final By HOME_PAGE_LOGO=By.xpath("//a[@aria-label='store logo']");
    public static final By CREATE_AN_ACCOUNT_LINK=By.linkText("Create an Account");
    /**
     * USER REGISTRATION
     */
    public static final By HEADER_TAG= By.xpath("//h1/span");
    public static final By FIRST_NAME=By.id("firstname");
    public static final By LAST_NAME=By.id("lastname");
    public static final By EMAIL=By.id("email_address");
    public static final By PASSWORD=By.id("password");
    public static final By CONFIRM_PASSWORD=By.id("password-confirmation");
    public static final By CREATE_ACCOUNT_BUTTON=By.xpath("//button[@title='Create an Account']/span");

    public static final By PAGE_TITLE=By.xpath("//div[@class='step-title']");
    /**
     * SHOPPING_CART
     */
    public static final By SEARCH_ITEM=By.id("search");
    public static final By SEARCH_OPTION=By.xpath("//li[@role='option']");
    public static final By PRODUCT_ITEM=By.xpath("//li[contains(@class,'product-item')]");
    public static final By ADD_TO_CART_BUTTON=By.id("product-addtocart-button");
    public static final By SHOPPING_CART_LINK=By.xpath("//a[text()='shopping cart']");
    public static final By PROCEED_TO_CHECKOUT_BUTTON=By.xpath("//button[@title='Proceed to Checkout']/span");
    /**
     * SHIPPING ADDRESS
     */
    public static final By SHIPPING_EMAIL=By.name("username");
    public static final By SHIPPING_FIRST_NAME=By.name("firstname");
    public static final By SHIPPING_LAST_NAME=By.name("lastname");
    public static final By COMPANY=By.name("company");
    public static final By STREET=By.name("street[0]");
    public static final By CITY=By.name("city");
    public static final By STATE=By.xpath("//select[@name='region_id']");
    public static final By ZIP_CODE=By.name("postcode");
    public static final By PHONE=By.name("telephone");
    /**
     * PLACE ORDER
     */
    public static final By SELECT_CHECKBOX_PLACE_ORDER=By.name("billing-address-same-as-shipping");
    public static final By PLACE_ORDER=By.xpath("//button[@title='Place Order']");

}
