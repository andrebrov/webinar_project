package org.andrebrov.testcases.seleniumtwo.common;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

/**
 * User: Andrey Rebrov
 * E-mail: andrebrov@gmail.com
 * Date: 16.05.12
 * Time: 11:34
 * Project: Dislike
 */

/**
 * This abstract test contains link on selenium config, webdriver instance and can do some stuff
 * before and after each test method
 */
@ContextConfiguration("classpath:service-context.xml")
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void printTestName(Method method) {
    }

    @AfterMethod(alwaysRun = true)
    public void clearCookies(Method method) throws Exception {
    }

    public WebDriver getDriver() {
        return driver;
    }
}
