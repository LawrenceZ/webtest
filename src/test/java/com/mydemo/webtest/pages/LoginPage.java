package com.mydemo.webtest.pages;

import com.microsoft.playwright.Page;
import com.mydemo.webtest.browser.BrowserConfig;

public class LoginPage {
    Page page = BrowserConfig.localPage;

    private String txtUsername = "#username";
    private String txtPassword = "#password";
    private String btnSignIn = "button[data-test='signin-submit']";
    private String linkSignUp = "a[data-test='signup']";

    public HomePage logIn(String userName, String password) {
        page.fill(txtUsername, userName);
        page.fill(txtPassword, password);
        page.click(btnSignIn);
        return new HomePage();
    }

    public SignUpPage openSignUpPage() {
        //add this fill just_placeholder to bypass the "broken" username null check
        // when clicking the Sign Up link
        page.fill(txtUsername, "just_placeholder");
        page.click(linkSignUp);
        return new SignUpPage();
    }


}
