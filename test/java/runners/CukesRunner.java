package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
@CucumberOptions(
				plugin = {"pretty", 
				"html:target/cucumber-report"
		},
		tags = "@LogIn",
		features= "src/test/resources/com/twitter/Features",
		glue="stepDefs",
		dryRun=false
)
		
public class CukesRunner extends AbstractTestNGCucumberTests {

}
