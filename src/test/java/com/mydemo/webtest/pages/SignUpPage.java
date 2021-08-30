package com.mydemo.webtest.pages;

import com.microsoft.playwright.Page;
import com.mydemo.webtest.browser.BrowserConfig;

public class SignUpPage {
    Page page = BrowserConfig.localPage;

    private String txtFirstName = "#firstName";
    private String txtLastName = "#lastName";
    private String txtUserName = "#username";
    private String txtPassword = "#password";
    private String txtConfirmPassword = "#confirmPassword";
    private String btnSignUp = "button[data-test='signup-submit']";

    public LoginPage signUp(String firstName, String lastName, String userName, String password, String confirmPassword) {
        page.fill(txtFirstName, firstName);
        page.fill(txtLastName, lastName);
        page.fill(txtUserName, userName);
        page.fill(txtPassword, password);
        page.fill(txtConfirmPassword, confirmPassword);
        page.click(btnSignUp);
        return new LoginPage();
    }

}
