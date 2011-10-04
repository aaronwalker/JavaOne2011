package com.base2.javaone.demos.simple.cdi;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.weld.WeldStep;

import javax.inject.Inject;
import javax.inject.Singleton;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * RPNCalculator CDI Step Class
 *
 * @author aaronwalker
 */
@WeldStep @Singleton
public class RPNCalculatorSteps {

    @Inject
    private RpnCalculator calculator;

    @Given("I am a math geek who likes RPN style calculators")
    public void  createRpnCalculator() {
        assertThat(calculator,notNullValue());
    }

    @When("I enter %num")
    public void enterNumber(@Named("num")Number num) {
        calculator.push(num);
    }

    @When("I press add")
    public void add() {
        calculator.push("+");
    }

    @When("I press subtract")
    public void subtract() {
        calculator.push("-");
    }

    @Then("the result is %expectedResult")
    public void assertResult(@Named("no1") Number expectedResult) {
        assertThat(calculator.value().longValue(),equalTo(expectedResult));
    }
}
