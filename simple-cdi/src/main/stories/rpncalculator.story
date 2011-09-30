JavaOne 2011 Demos - Simple RPN Calculator Story

Meta:
@author aaronwalker
@themes cdi

Narrative:
In order to perform basic arithmetic
as a math geek I want to perform arithmetic
using an RPN style calculator

Scenario: Basic Arithmetic - addition of two numbers

Given I am a math geek who likes RPN style calculators
When I enter 4
And I enter 5
And I press add
Then the result is 9

Scenario: Basic Arithmetic - subtract of two numbers

Given I am a math geek who likes RPN style calculators
When I enter 9
And I enter 5
And I press subtract
Then the result is 4

Scenario: Basic Arithmetic - addition and subtraction

Given I am a math geek who likes RPN style calculators
When I enter 9
And I enter 5
And I press add
And I enter 14
And I press subtract
Then the result is 0




