package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

public class testRunner {
    // This class remains empty. It only uses annotations to configure Cucumber.
}

@RunWith(Cucumber.class)
@CucumberOptions(
        // Path to the Feature files
        features = "src/test/resources/features",
        // Path to the Step Definitions
        glue = "stepdefinitions",
        // Reports
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        // Only run scenarios without the @ignore tag
        tags = "not @ignore"
)
