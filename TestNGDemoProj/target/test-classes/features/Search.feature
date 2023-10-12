Feature: Search
User should be able to search by providing proper details

@search @validproduct @smoke @regression @all
Scenario: Search for a valid product
Given User has opened the Application url
When User enters valid product into the search box field
And Clicks on Search button
Then Valid product should be displayed in the search results

@search @invalidproduct @smoke @regression @all
Scenario: Search for an invalid product
Given User has opened the Application url
When User enters invalid product into the search box field
And Clicks on Search button
Then Proper message about no search results should be displayed

@search @noproduct @regression @all
Scenario: Search without providing any product
Given User has opened the Application url
When User dont enter any product into the search box field
And Clicks on Search button
Then Proper message about no search results should be displayed