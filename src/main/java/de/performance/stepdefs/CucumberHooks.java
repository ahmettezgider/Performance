package de.performance.stepdefs;

import de.performance.webdriver.DriverProvider;
import io.cucumber.java.*;

public class CucumberHooks {


    @Before
    public void before() {

    }

    @After
    public void after(Scenario scenario)  {

        DriverProvider.quit();


    }

}
