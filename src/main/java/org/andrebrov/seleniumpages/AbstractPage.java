package org.andrebrov.seleniumpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;

/**
 * User: Andrey Rebrov
 * E-mail: andrebrov@gmail.com
 * Date: 16.05.12
 * Time: 11:08
 */
/*
 * Abstract page, that contains load methods, typical methods (i.e. web elements interactions),
 * wait mechanism and so on
 */
public abstract class AbstractPage extends LoadableComponent<LoginPage> {

    private final static Logger LOG = LoggerFactory.getLogger(AbstractPage.class);

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private static final long DEFAULT_TIMEOUT = 30;
    public static final String URL = "http://n.mail.km.ru";

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        js = (JavascriptExecutor) driver;
        //init element marked wit FindBy annotation
        PageFactory.initElements(driver, this);
        LOG.debug("Page has been loaded");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    //defines how to typilcally load page
    @Override
    protected void load() {
        getDriver().get(URL);
    }

    //this method ensure that page has been loaded
    @Override
    protected void isLoaded() throws Error {
        List<By> pageLoadedCheckElementLocator = getPageLoadedCheckElementLocator();
        assertTrue("No pageLoadedCheckElementLocator", pageLoadedCheckElementLocator != null);
        assert pageLoadedCheckElementLocator != null;
        assertTrue("No pageLoadedCheckElementLocator", !pageLoadedCheckElementLocator.isEmpty());
        for (By by : pageLoadedCheckElementLocator) {
            assertTrue("Element " + by.toString() + " not found.", getDriver().findElement(by).isDisplayed());
        }
    }

    //this method return list of search criterias of webelements that should be present on page
    //to make us sure that page is fully loaded
    protected abstract List<By> getPageLoadedCheckElementLocator();

    // Waits
    public WebElement waitUntilFound(final By by) {
        wait.until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed();
            }
        });
        return driver.findElement(by);
    }

    public void waitForAjaxComplete() {
        wait.until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return (Boolean) js.executeScript("return $.active == 0");
            }
        });
    }

}
