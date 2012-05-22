package org.andrebrov.seleniumpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;

/**
 * User: Andrey Rebrov
 * E-mail: andrebrov@gmail.com
 * Date: 16.05.12
 * Time: 11:16
 */

/**
 * MainPage extends LoginPage because when we start  application we can`t know
 * do we already login or not, so it means that LoginPage also should have method login
 */
public class MainPage extends LoginPage {

    private final static Logger LOG = LoggerFactory.getLogger(MainPage.class);

    public MainPage(WebDriver driver) {
        super(driver);
    }

    //tag with css class greeting contains user name
    @Override
    protected List<By> getPageLoadedCheckElementLocator() {
        List<By> elements = new ArrayList<By>();
        elements.add(By.className("greeting"));
        return elements;
    }

    @Override
    public MainPage get() {
        return (MainPage)super.get();
    }
}
