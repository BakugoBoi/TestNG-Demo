Feature: Login
User should be able to login to the application with proper credentials

Background:
Given User has opened the Application URL
And Navigated to Login page

@login @validcredentials @datadriven @smoke @regression @all
Scenario Outline: Login with valid credentials
When User enters valid email as <email> and valid password as <password> into the fields
And Clicks on Login button
Then User should be able to login successful
Examples:
| email 									| password 	|
| amotooricap9@gmail.com	| 12345			|
| amotooricap1@gmail.com	| 12345			|
|	amotooricap3@gmail.com	| 12345			|

@login @invalidemail @regression @all
Scenario: Login with invalid email and valid password
When User enter invalid email and valid password into the fields
And Clicks on Login button
Then User should see a warning message instead of getting loggedin

@login @invalidpassword @regression @all
Scenario: Login with valid email and invalid password
When User enter valid email and invalid pasword into the fields
And Clicks on Login button
Then User should see a warning message instead of getting loggedin 

@login @invalidcredentials @smoke @regression @all
Scenario: Login with invalid credentials
When User enter invalid email and invalid password into the fields
And Clicks on Login button
Then User should see a warning message instead of getting loggedin

@login @nocredentials @regression @all
Scenario: Login without providing any credentials
When User dont enter email and password into the fields
And Clicks on Login button
Then User should see a warning message instead of getting loggedin






