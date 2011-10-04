JavaOne 2011 Demos - Customer Demo

Meta:
@author aaronwalker
@themes web

Narrative:
In order to manage customer records
as a Customer Service Operator
I want to be able to create, edit and delete customer records
and notify the billing system of any changes to customers

Scenario: Create a new customer record

Given I am on the Customer list page
And I click on the Create New link
When I create customers the following:
|firstname|lastname|
|Aaron|Walker|
|Dan|North|
Then a new customer record should have been created
And the billing system should receive a customer create event on the testQueue for each created customer

