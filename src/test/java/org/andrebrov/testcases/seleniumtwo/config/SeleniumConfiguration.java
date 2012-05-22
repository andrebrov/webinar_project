package org.andrebrov.testcases.seleniumtwo.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * User: Andrey Rebrov
 * E-mail: andrebrov@gmail.com
 * Date: 16.05.12
 * Time: 11:42
 */

/**
 * This class uses spring notations to configure webdriver as spring bean.
 * This class also shutdown webdriver instance after all tests
 */
@Configuration
public class SeleniumConfiguration {

    @Autowired
    private WebDriver driver;

    public
    @Bean
    WebDriver driver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, "true");
        return new InternetExplorerDriver(capabilities);
    }

    @PreDestroy
    public void cleanUp() {
        try {
            driver.quit();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}

