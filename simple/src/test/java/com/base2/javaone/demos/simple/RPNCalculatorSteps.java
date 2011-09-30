package com.base2.javaone.demos.simple;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * RPNCalculator Step Class
 *
 * @author aaronwalker
 */
public class RPNCalculatorSteps  {

    private RpnCalculator calculator;

    @Given("I am a math geek who likes RPN style calculators")
    public void  createRpnCalculator() {
        calculator = new RpnCalculator();
    }

    @When("I enter %num")
    public void enterNumber(@Named("num")Number num) {
        calculator.push(num);
    }

    @When("I press add")
    public void add() {
        calculator.push("+");
    }

    @Then("the result is %expectedResult")
    public void assertResult(@Named("no1") Number expectedResult) {
        assertThat(calculator.value().longValue(),equalTo(expectedResult));
    }
}
