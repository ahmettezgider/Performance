package de.performance.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"C:\\vvc\\features"},
        // features = "src/main/resources/features/Test.feature",
        glue = {"de.performance.stepdefs"},
        dryRun = true,
        plugin = {
                "pretty",
                "junit:cucumber-report/result.xml",
                "html:cucumber-report/cucumber-pretty",
                "json:cucumber-report/CucumberTestReport.json",
                "rerun:cucumber-report/rerun.txt"
        }

)
// @Listeners( {de.vvc.ikaros40.tests.CucumberTestListener.class})
public class Tests {

}
