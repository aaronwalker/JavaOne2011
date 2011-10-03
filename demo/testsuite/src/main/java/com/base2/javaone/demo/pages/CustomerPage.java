package com.base2.javaone.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Customer Page
 *
 * @author aaronwalker
 */
@ApplicationScoped
public class CustomerPage extends BasePage {

    private static final String PAGE ="/customer";
    private Map<String,Map<String,String>> currentCustomers = new HashMap<String, Map<String, String>>();

    public void visitCreateCustomer() {
        WebElement element = webDriver.findElement(By.linkText("Create new"));
        element.click();
    }

    public String createCustomer(Map<String,String> customer) {
        WebElement firstName = webDriver.findElement(By.id("form:customerBeanCustomerFirstName"));
        firstName.sendKeys(customer.get("firstname"));
        WebElement lastName = webDriver.findElement(By.id("form:customerBeanCustomerLastName"));
        lastName.sendKeys(customer.get("lastname"));

        WebElement form = webDriver.findElement(By.linkText("Create"));
        form.click();

        String url = webDriver.getCurrentUrl();

        String id = url.substring(url.lastIndexOf("/")+1,url.length());
        currentCustomers.put(id,customer);

        assertCustomerViewPage(id);

        return id;
    }

    public void assertAmOnPage() {
        assertAmOnPage(PAGE);
    }

    public void assertAmOnPage(String page) {
        assertThat(webDriver.getCurrentUrl(), endsWith(page));
        WebElement element = webDriver.findElement(By.xpath("//h1"));
        assertThat(element.getText(), containsString("Customer"));
    }

    public void verifyCustomer(String customerId) {
        visit(page() + "/" + customerId);
        assertAmOnPage(PAGE + "/" + customerId);
        assertCustomerViewPage(customerId);
    }

    @Override
    protected String page() {
        return PAGE;
    }

    private void assertCustomerViewPage(String customerId) {
        WebElement firstNameResult = webDriver.findElement(By.id("form:customerBeanCustomerFirstName"));
        WebElement lastNameResult = webDriver.findElement(By.id("form:customerBeanCustomerLastName"));
        assertThat(firstNameResult.getText(),equalTo(currentCustomers.get(customerId).get("firstname")));
        assertThat(lastNameResult.getText(),equalTo(currentCustomers.get(customerId).get("lastname")));
    }
}
