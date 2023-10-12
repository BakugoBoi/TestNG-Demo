package stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageobjects.HomePage;
import pageobjects.SearchPage;

public class Search extends Base {
	
	WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	
	@Before("@search")
	public void setup() throws IOException {
		loadPropertiesFile();
		driver = openBrowser(prop.getProperty("browserName"));
	}
	
	@After("@search")
	public void tearDown() {
		
		driver.quit();
	}
	
	@Given("^User has opened the Application url$")
    public void user_has_opened_the_application_url() throws IOException {
      
        driver.get(prop.getProperty("url"));
    }

    @When("^User enters valid product into the search box field$")
    public void user_enters_valid_product_into_the_search_box_field() {
    	
    	homePage = new HomePage(driver);
    	homePage.enterProductDetailsIntoSearchBoxField(prop.getProperty("validProduct"));
        
    }
    
    @And("^Clicks on Search button$")
    public void clicks_on_search_button()  {
    	
    	searchPage = homePage.clickOnSearchButton();
       
    }
    
    @Then("^Valid product should be displayed in the search results$")
    public void valid_product_should_be_displayed_in_the_search_results()  {
    	
    	Assert.assertTrue(searchPage.displayStatusOfValidProduct());
    	
        
    }

    @When("^User enters invalid product into the search box field$")
    public void user_enters_invalid_product_into_the_search_box_field() {
    	
    	homePage = new HomePage(driver);
    	homePage.enterProductDetailsIntoSearchBoxField(prop.getProperty("invalidProduct"));
        
    }
    
    @Then("^Proper message about no search results should be displayed$")
    public void proper_message_about_no_search_results_should_be_displayed()  {
    	
		Assert.assertEquals(searchPage.retrieveNoProductMatchesMessage(),prop.getProperty("noProductMatchesMessage"));
		
        
    }

    @When("^User dont enter any product into the search box field$")
    public void user_dont_enter_any_product_into_the_search_box_field() {
    	
    	homePage = new HomePage(driver);
    	homePage.enterProductDetailsIntoSearchBoxField("");
       
    }

    

    

    

}
