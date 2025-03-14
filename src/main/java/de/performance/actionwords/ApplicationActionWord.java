package de.performance.actionwords;

import de.performance.config.TestConfiguration;
import de.performance.config.TestConfigurationFactory;
import de.performance.controls.WebElementControl;
import de.performance.webdriver.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.IOException;
import java.time.Duration;


public class ApplicationActionWord {

    public void startApp() throws IOException {
        TestConfiguration config = TestConfigurationFactory.instance().getTestConfiguration();
        DriverProvider.configure(config);
        DriverProvider.getDriver().manage().window().maximize();
        DriverProvider.getDriver().get(config.getApplication().getUrl());
    }


    public static void login() throws Exception {
        TestConfiguration config = TestConfigurationFactory.instance().getTestConfiguration();
        TextfieldActionWords.setText("Username", config.getApplication().getUsername());
        TextfieldActionWords.setText("Password", config.getApplication().getPassword());
        ButtonActionWords.click("Login");
        new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(20))
                .until(driver -> {
                    try {
                        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
                        return true;
                    }catch (Exception e){
                        return false;
                    }
                });
    }



}
