package com.base2.javaone.demo.steps;

import com.base2.javaone.demo.jms.JMSReceiver;
import com.base2.javaone.demo.pages.CustomerPage;
import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.weld.WeldStep;
import org.jbehave.core.model.ExamplesTable;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Customer step class
 *
 * @author aaronwalker
 */
@WeldStep @Singleton
public class CustomerSteps {

    @Inject
    private CustomerPage customerPage;

    @Inject
    JMSReceiver jms;

    private List<String> customerIds = new ArrayList<String>();

    @Given("I am on the Customer list page")
    public void visitCustomerPage() {
        customerPage.visit();
        customerPage.assertAmOnPage();
    }

    @Given("I click on the Create New link")
    public void clickCustomerCreateLink() {
        customerPage.visitCreateCustomer();
        customerPage.assertAmOnPage("create");
    }

    @When("I create customers the following:  %customer")
    public void enterCustomer(@Named("customer")ExamplesTable customerData) {
        for(Map<String,String> row : customerData.getRows()) {
            String customerId = customerPage.createCustomer(row);
            assertThat(customerId,notNullValue());
            customerIds.add(customerId);
            customerPage.visitCreateCustomer();
        }
    }

    @Then("a new customer record should have been created")
    public void verifyCustomer() {
        for(String customerId : customerIds) {
            customerPage.verifyCustomer(customerId);
        }
    }

    @Then("the billing system should receive a customer create event on the %queue for each created customer")
    public void verifyBillingCustomer(@Named("queue")String queue) {
        for(String customerId : customerIds) {
            TextMessage msg = (TextMessage)jms.receiveMessage(queue);
            assertThat(msg,is(notNullValue()));
        }
    }
}
