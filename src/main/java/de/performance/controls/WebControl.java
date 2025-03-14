package de.performance.controls;

import de.performance.webdriver.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

abstract class WebControl {

    By controlRootLocator;
    WebDriver driver;
    String label = "";

    public WebControl(By locator) {
        controlRootLocator = locator;
        label = locator.toString();
        driver = DriverProvider.getDriver();
    }


    WebElement getRootElement() {
        WebElement rootElement = WebElementHelper.waitForControl(this.controlRootLocator);
        return rootElement;
    }


    WebElement getRootElementNoWait() {
        WebElement rootElement = WebElementHelper.waitForControl(this.controlRootLocator);
        return rootElement;
    }

}
