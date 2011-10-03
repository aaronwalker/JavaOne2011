package com.base2.javaone.demo.pages;

import org.openqa.selenium.WebDriver;

import javax.inject.Inject;

/**
 * Base Page Object
 *
 * @author aaronwalker
 */
public abstract class BasePage {

    protected static final String BASE_URL = "http://localhost:8080/demo-web";

    @Inject
    protected WebDriver webDriver;

    public void visit() {
        webDriver.get(BASE_URL + page());
    }

    public void visit(String page) {
        webDriver.get(BASE_URL + page);
    }

    protected abstract String page();

}
