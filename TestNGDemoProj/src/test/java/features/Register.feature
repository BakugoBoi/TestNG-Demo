Feature: Register
User should be able to create account with proper details

@register @mandatoryfields @smoke @regression @all
Scenario: Register by providing only mandatory fields
Given User has opened the application URL
And Navigated to Register page
When User enters only mandatory fields
|firstName|arun|
|lastName|motoori|
|telephone|1234567890|
|password|12345|
And Clicks on Continue button
Then User account should be created successfully

@register @allfields @smoke @regression @all
Scenario: Register by providing all fields
Given User has opened the application URL
And Navigated to Register page
When User enters all the fields
And Clicks on Continue button
Then User account should be created successfully

@register @nofieldsprovided @regression @all
Scenario: Register without providing any fields
Given User has opened the application URL
And Navigated to Register page
When User dont enter any field
And Clicks on Continue button
Then Proper warning messages should be displayed without creating an account

@register @duplicateemail @regression @all
Scenario: Register with a dupliate email address
Given User has opened the application URL
And Navigated to Register page
When User enters all the fields except email address field
And Provides email address of the account which already exists into the email field
And Clicks on Continue button
Then Proper warning message informing User about duplicate account should be displayed



