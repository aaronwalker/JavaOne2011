JavaOne 2011 Demos - Customer Demo

Meta:
@author aaronwalker
@themes web

Narrative:
In order to manage customer records
as a Customer Service Operator
I want to be able to create, edit and delete customer records

Scenario: Create a new customer record

Given I am on the Customer list page
And I click on the Create New link
When I create customers the following:
|firstname|lastname|
|Aaron|Walker|
|Mauro|Talevi|
Then a new customer record should have been created
And I Should receive a customer update event on the testQueue queue for each created customer

