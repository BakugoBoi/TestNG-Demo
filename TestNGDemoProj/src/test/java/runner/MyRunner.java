package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(	plugin={"html:target/cucumberReport.html"},
					features="src/test/java/features/Login.feature",
					glue="stepdefinitions",
					tags="@datadriven")
public class MyRunner extends AbstractTestNGCucumberTests {
	
	

}
