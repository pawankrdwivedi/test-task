package com.test.task.test.or;

import org.openqa.selenium.By;

public class ParaBankOR {
    /**
     * HOME PAGE
     */
    public static final By HEADER_TAG =By.tagName("h1");
    public static final By HEADER_PANEL=By.id("headerPanel");
    /**
     * MENU BAR
     */
    public static final By REGISTER_LINK=By.linkText("Register");
    public static final By BILL_PAY_LINK=By.linkText("Bill Pay");
    /**
     * REGISTER PAGE
     */
    public static final By FIRST_NAME=By.id("customer.firstName");
    public static final By LAST_NAME=By.id("customer.lastName");
    public static final By ADDRESS=By.id("customer.address.street");
    public static final By CITY=By.id("customer.address.city");
    public static final By STATE=By.id("customer.address.state");
    public static final By ZIP_CODE=By.id("customer.address.zipCode");
    public static final By PHONE_NUMBER=By.id("customer.phoneNumber");
    public static final By SSN=By.id("customer.ssn");
    public static final By REG_USER_NAME=By.id("customer.username");
    public static final By REG_PASSWORD=By.id("customer.password");
    public static final By CONFIRM_PASSWORD=By.id("repeatedPassword");
    public static final By REGISTER_BUTTON=By.xpath("//input[@value='Register']");
    /**
     * BILL PAYMENT PAGE
     */
    public static final By PAYEE_NAME=By.name("payee.name");
    public static final By PAYEE_ADDRESS=By.name("payee.address.street");
    public static final By PAYEE_CITY=By.name("payee.address.city");
    public static final By PAYEE_STATE=By.name("payee.address.state");
    public static final By PAYEE_ZIP_CODE=By.name("payee.address.zipCode");
    public static final By PAYEE_PHONE=By.name("payee.phoneNumber");
    public static final By PAYEE_ACCOUNT=By.name("payee.accountNumber");
    public static final By PAYEE_CONFIRM_ACCOUNT=By.name("verifyAccount");
    public static final By PAYEE_AMOUNT=By.name("amount");
    public static final By SEND_PAYMENT_BUTTON =By.xpath("//input[@value='Send Payment']");
    /**
     * BILL PAYMENT CONFIRMATION PAGE
     */
    public static final By PAYEE_CONFIRMATION_NAME =By.id("payeeName");
    public static final By PAYEE_CONFIRMATION_AMOUNT =By.id("amount");
}
