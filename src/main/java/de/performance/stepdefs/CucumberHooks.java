package de.performance.stepdefs;

import de.performance.controls.WebElementControl;
import de.performance.util.TestLogger;
import de.performance.util.Utils;
import de.performance.webdriver.DriverProvider;
import io.cucumber.java.*;

import java.util.logging.Logger;

public class CucumberHooks {


    @Before
    public void before() {

    }

    @After
    public void after(Scenario scenario)  {

        DriverProvider.quit();
        TestLogger.logWrite("App close");

    }

}
