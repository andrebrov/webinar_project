package org.andrebrov.testcases.seleniumone;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Webinar
 * Date: 21.05.12
 * Time: 20:47
 */
//simple test that uses Selenium 1 style
public class LoginTest {

    private static final String MAIL_HOST = "http://n.mail.km.ru";
    private static final String MAIL_LOGIN = "selenium_testing";
    private static final String MAIL_SUFFIX = "@km.ru";
    private static final String MAIL_PASSWORD = "webdriver123";

    private WebDriver webDriver;
    private Selenium selenium;

    @BeforeTest
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //next config lets avoid some problems with OE
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, "true");
        webDriver = new InternetExplorerDriver(capabilities);
        selenium = new WebDriverBackedSelenium(webDriver, MAIL_HOST);
    }

    @Test
    public void testLogin() {
        selenium.open("/");
        //we should check that we are really on login page
        assertTrue("Login field missed", selenium.isElementPresent("user_login"));
        assertTrue("Password field missed", selenium.isElementPresent("user_password"));
        assertTrue("Login button missed", selenium.isElementPresent("op"));
        selenium.type("user_login", MAIL_LOGIN);
        selenium.type("user_password", MAIL_PASSWORD);
        selenium.click("op");
        //wait for some random time - 30 sec
        selenium.waitForPageToLoad("30000");
        String userName = selenium.getText("class=greeting");
        assertEquals("Wrong user name!", "Здравствуйте, "+MAIL_LOGIN + MAIL_SUFFIX, userName);
    }

    @AfterTest
    public void tearDown() {
        //don`t forget to shutdown browser
        try {
            webDriver.quit();
        } catch (Exception e) {

        }
    }


}
