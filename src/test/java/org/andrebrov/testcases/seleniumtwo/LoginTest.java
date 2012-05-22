package org.andrebrov.testcases.seleniumtwo;

import org.andrebrov.seleniumpages.AbstractPage;
import org.andrebrov.seleniumpages.LoginPage;
import org.andrebrov.seleniumpages.MainPage;
import org.andrebrov.testcases.seleniumtwo.common.AbstractTest;
import org.andrebrov.testcases.seleniumtwo.dataproviders.LoginDataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Webinar
 * Date: 21.05.12
 * Time: 20:47
 */
public class LoginTest  extends AbstractTest {

    //we use TestNG dataProvider feature to setup data for test
    @Test(dataProvider = "loginCredentials",dataProviderClass = LoginDataProvider.class)
    public void testLogin(String login, String password) {
        getDriver().get(AbstractPage.URL);
        LoginPage loginPage = new LoginPage(getDriver()).get();
        AbstractPage mainPage = loginPage.login(login, password);
        assertTrue("Not on main page",mainPage instanceof MainPage);
    }
}
