package org.andrebrov.testcases.seleniumtwo.dataproviders;

import org.testng.annotations.DataProvider;

/**
 * Created by IntelliJ IDEA.
 * User: Webinar
 * Date: 21.05.12
 * Time: 21:47
 */
public class LoginDataProvider {

    @DataProvider
    public static Object[][] loginCredentials() {
        Object[][] credentials = new Object[1][2];
        credentials[0][0] = "selenium_testing";
        credentials[0][1] = "webdriver123";
        return credentials;
    }
}
