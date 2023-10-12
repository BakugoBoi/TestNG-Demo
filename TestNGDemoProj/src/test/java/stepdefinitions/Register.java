package stepdefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import base.Base;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageobjects.AccountSuccessPage;
import pageobjects.HomePage;
import pageobjects.RegisterPage;

public class Register extends Base {
	
	WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	@Before("@register")
	public void setup() throws IOException {
		loadPropertiesFile();
		driver = openBrowser(prop.getProperty("browserName"));
	}
	
	@After("@register")
	public void tearDown() {
		
		driver.quit();
	}
	
	@Given("^User has opened the application URL$")
    public void user_has_opened_the_application_url() throws IOException  {
       
        driver.get(prop.getProperty("url"));
    }
	
	@And("^Navigated to Register page$")
    public void navigated_to_register_page()  {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		registerPage = homePage.selectRegisterOption();
        
    }

    @When("^User enters only mandatory fields$")
    public void user_enters_only_mandatory_fields(DataTable table)  {
    	
    	Map<String, String> map = table.asMap();
    
    	registerPage.enterFirstName(map.get("firstName"));
    	registerPage.enterLastName(map.get("lastName"));
    	registerPage.enterEmailAddress("amotooricap"+generateTimeStamp()+"@gmail.com");
        registerPage.enterTelephoneNumber(map.get("telephone"));
        registerPage.enterPassword(map.get("password"));
        registerPage.enterConfirmPassword(map.get("password"));
        registerPage.selectPrivacyPolicy();
    }
    
    @And("^Clicks on Continue button$")
    public void clicks_on_continue_button() {
    	
    	accountSuccessPage = registerPage.clickOnContinueButton();
        
    }
    
    @Then("^User account should be created successfully$")
    public void user_account_should_be_created_successfully()  {
    	
		Assert.assertEquals(accountSuccessPage.retrieveAccountCreatedHeading(),prop.getProperty("accountCreationSuccessMessage"));
		
    }

    @When("^User enters all the fields$")
    public void user_enters_all_the_fields()  {
    	
    	registerPage.enterFirstName(prop.getProperty("firstName"));
    	registerPage.enterLastName(prop.getProperty("lastName"));
    	registerPage.enterEmailAddress("amotooricap"+generateTimeStamp()+"@gmail.com");
        registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectYesForNewsletter();
        registerPage.selectPrivacyPolicy();
       
    }

    @When("^User dont enter any field$")
    public void user_dont_any_field()  {
    	
    	registerPage.enterFirstName("");
    	registerPage.enterLastName("");
    	registerPage.enterEmailAddress("");
        registerPage.enterTelephoneNumber("");
        registerPage.enterPassword("");
        registerPage.enterConfirmPassword("");
        
    }
    
    @Then("^Proper warning messages should be displayed without creating an account$")
    public void proper_warning_messages_should_be_displayed_without_creating_an_account()  {
     
    	Assert.assertTrue(registerPage.retrievePrivacyPolicyWarningMessage().contains(prop.getProperty("privacyPolicyWarning")));
		Assert.assertEquals(registerPage.retrieveFirstNameWarningMessage(),prop.getProperty("firstNameWarning"));
		Assert.assertEquals(registerPage.retrieveLastNameWarningMessage(),prop.getProperty("lastNameWarning"));
		Assert.assertEquals(registerPage.retrieveEmailWarningMessage(),prop.getProperty("emailWarning"));
		Assert.assertEquals(registerPage.retrieveTelephoneWarningMessage(),prop.getProperty("telephoneWarning"));
		Assert.assertEquals(registerPage.retrievePasswordWarningMessage(),prop.getProperty("passwordWarning"));
    	
    }

    @When("^User enters all the fields except email address field$")
    public void user_enters_all_the_fields_except_email_address_field() {
    	
    	registerPage.enterFirstName(prop.getProperty("firstName"));
    	registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectYesForNewsletter();
        registerPage.selectPrivacyPolicy();
        
    }
    
    @And("^Provides email address of the account which already exists into the email field$")
    public void provides_email_address_of_the_account_which_already_exists_into_the_email_field()  {
        
    	registerPage.enterEmailAddress(prop.getProperty("validEmailOne"));
    }

   
    @Then("^Proper warning message informing User about duplicate account should be displayed$")
    public void proper_warning_message_informing_user_about_duplicate_account_should_be_displayed()  {
       
		Assert.assertTrue(registerPage.retrieveDuplicateEmailWarningMessage().contains(prop.getProperty("duplicateEmailWarning")));
		
    }
}
