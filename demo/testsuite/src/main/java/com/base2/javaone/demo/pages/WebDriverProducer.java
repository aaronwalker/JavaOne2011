package com.base2.javaone.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Configure Web Driver
 *
 * @author aaronwalker
 */
@ApplicationScoped
public class WebDriverProducer {

    @Produces
    public WebDriver webDriverProducer() {
        FirefoxProfile profile = new FirefoxProfile();
        return new FirefoxDriver(profile);
    }
}
