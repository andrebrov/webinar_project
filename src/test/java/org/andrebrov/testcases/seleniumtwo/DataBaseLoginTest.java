package org.andrebrov.testcases.seleniumtwo;

import org.andrebrov.seleniumpages.AbstractPage;
import org.andrebrov.seleniumpages.LoginPage;
import org.andrebrov.seleniumpages.MainPage;
import org.andrebrov.testcases.seleniumtwo.common.AbstractTest;
import org.andrebrov.testcases.seleniumtwo.common.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * User: Andrey Rebrov
 * E-mail: andrebrov@gmail.com
 * Date: 16.05.12
 * Time: 10:57
 */
public class DataBaseLoginTest extends AbstractTest {

    //Autowired is a spring annotation that lets us to inject existing instance of mentioned class
    //without creating new one
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DataProvider
    public Object[][] loginCredentials() {
        //execute some select from db
        List<Pair> result = jdbcTemplate.query("select login,password from logindata limit 1", new RowMapper<Pair>() {
            //RowmMapper should map sql query result to java obkect
            @Override
            public Pair mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Pair(resultSet.getString(1), resultSet.getString(2));
            }
        });
        Object[][] credentials = new Object[1][2];
        Pair pair = result.get(0);
        credentials[0][0] = pair.getFirst();
        credentials[0][1] = pair.getSecond();
        return credentials;
    }

    @Test(dataProvider = "loginCredentials")
    public void testLogin(String login, String password) {
        getDriver().get(AbstractPage.URL);
        LoginPage loginPage = new LoginPage(getDriver()).get();
        AbstractPage mainPage = loginPage.login(login, password);
        //check that we are on MainPage
        assertTrue("Not on main page",mainPage instanceof MainPage);
    }

}
