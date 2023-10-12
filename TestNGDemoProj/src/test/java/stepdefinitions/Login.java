package stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageobjects.AccountPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public class Login extends Base{
	
	WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;
	
	@Before("@login")
	public void setup() throws IOException {
		loadPropertiesFile();
		driver = openBrowser(prop.getProperty("browserName"));
	}
	
	@After("@login")
	public void tearDown() {
		
		driver.quit();
	}
	
	@Given("^User has opened the Application URL$")
    public void user_has_opened_the_application_url()  {
			
		driver.get(prop.getProperty("url"));
        
    }
	
	@And("^Navigated to Login page$")
    public void navigated_to_login_page()  {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		loginPage = homePage.selectLoginOption();
        
    }
	
	@When("^User enters valid email as (.+) and valid password as (.+) into the fields$")
    public void user_enters_valid_email_as_and_valid_password_as_into_the_fields(String email, String password)  {
        
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
    }
    
    @And("^Clicks on Login button$")
    public void clicks_on_login_button() {
    	
    	accountPage = loginPage.clickOnLoginButton();
        
    }
    
    @Then("^User should be able to login successful$")
    public void user_should_be_able_to_login_successful()  {
    	
    	Assert.assertTrue(accountPage.displayStatusOfEditYourAccountInformationLink());
        
    }

    @When("^User enter invalid email and valid password into the fields$")
    public void user_enter_invalid_email_and_valid_password_into_the_fields()  {
        
    	loginPage.enterEmailAddress("amotooricap"+generateTimeStamp()+"@gmail.com");
    	loginPage.enterPassword(prop.getProperty("validPassword"));
    	
    }
    
    @Then("^User should see a warning message instead of getting loggedin$")
    public void user_should_see_a_warning_message()  {
    	
		Assert.assertTrue(loginPage.retrieveTextOfEmailPasswordNotMatchingWarningMessage().contains(prop.getProperty("emailPasswordNoMatchWarning")));
		driver.quit();
    
    }

    @When("^User enter valid email and invalid pasword into the fields$")
    public void user_enter_valid_email_and_invalid_pasword_into_the_fields()  {
    	
    	loginPage.enterEmailAddress(prop.getProperty("validEmailOne"));
    	loginPage.enterPassword(prop.getProperty("invalidPassword"));
        
    }

    @When("^User enter invalid email and invalid password into the fields$")
    public void user_enter_invalid_email_and_invalid_password_into_the_fields()  {
       
    	loginPage.enterEmailAddress("amotooricap"+generateTimeStamp()+"@gmail.com");
    	loginPage.enterPassword(prop.getProperty("invalidPassword"));
    }

    @When("^User dont enter email and password into the fields$")
    public void user_dont_enter_email_and_password_into_the_fields()  {
    	
    	loginPage.enterEmailAddress("");
    	loginPage.enterPassword("");
        
    }   
}
