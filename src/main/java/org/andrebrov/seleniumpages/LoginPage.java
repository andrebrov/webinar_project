package org.andrebrov.seleniumpages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;

/**
 * User: Andrey Rebrov
 * E-mail: andrebrov@gmail.com
 * Date: 16.05.12
 * Time: 11:07
 */

/**
 * This page describes login page
 */
public class LoginPage extends AbstractPage {

    private final static Logger LOG = LoggerFactory.getLogger(LoginPage.class);

    //FindBy annotations makes easier to map dom elements to class fields
    @FindBy(name = "user_login")
    private WebElement usernameInput;
    @FindBy(name = "user_password")
    private WebElement passwordInput;
    @FindBy(name = "op")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //To be sure, that login page is present, we should see login and password fields and login button
    @Override
    protected List<By> getPageLoadedCheckElementLocator() {
        List<By> elements = new ArrayList<By>();
        elements.add(By.name("user_login"));
        elements.add(By.name("user_password"));
        elements.add(By.name("op"));
        return elements;
    }

    public AbstractPage login(String login, String password) {
        usernameInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.submit();
        try {
            //try to find alert with error message about wrong login or password
            getDriver().findElement(By.className("msg-header"));
            return this;
        } catch (NoSuchElementException e) {
            //wait until MainPage has been loaded
            waitUntilFound(By.className("greeting"));
            return new MainPage(getDriver()).get();
        }
    }

    @Override
    public LoginPage get() {
        return super.get();
    }
}
