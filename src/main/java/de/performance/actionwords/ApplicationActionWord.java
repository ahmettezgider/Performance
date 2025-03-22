package de.performance.actionwords;

import de.performance.config.TestConfiguration;
import de.performance.config.TestConfigurationFactory;
import de.performance.util.TestLogger;
import de.performance.webdriver.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.logging.Logger;


public class ApplicationActionWord {

    private static Logger logger;

    public void startApp() throws IOException {
        TestConfiguration config = TestConfigurationFactory.instance().getTestConfiguration();
        TestLogger.logWrite("Configure Browser");
        DriverProvider.configure(config);
        DriverProvider.getDriver().manage().window().maximize();
        DriverProvider.getDriver().get(config.getApplication().getUrl());
        TestLogger.logWrite("Application Started");
    }


    public static void login() throws Exception {
        TestConfiguration config = TestConfigurationFactory.instance().getTestConfiguration();
        TextfieldActionWords.setText("Username", config.getApplication().getUsername());
        TestLogger.logWrite("input Username");
        TextfieldActionWords.setText("Password", config.getApplication().getPassword());
        TestLogger.logWrite("input Password");
        ButtonActionWords.click("Login");
        TestLogger.logWrite("Login clicked");
        new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(20))
                .until(driver -> {
                    try {
                        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
                        return true;
                    }catch (Exception e){
                        return false;
                    }
                });
        TestLogger.logWrite("Loggedin");
    }



}
