package de.performance.controls;

import de.performance.tdexpressions.ExpressionEvaluator;
import de.performance.tdexpressions.InvalidExpressionException;
import de.performance.webdriver.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.time.Duration;

public class WebElementControl extends WebControl {

    private final static String ELEMENT_XPATH = "(//*[text()=\"{0}\"])[{1}]";
    public WebElementControl(By locator) {
        super(locator);
    }

    public static WebElementControl fromLabel(String label) throws InvalidExpressionException {
        return fromLabel(label, 1);
    }

    public static WebElementControl fromLabel(String label, int no) throws InvalidExpressionException {
        label = ExpressionEvaluator.evaluate(label);
        String xpath = MessageFormat.format(ELEMENT_XPATH, label, no);
        By locator = By.xpath(xpath);
        WebElementControl controller = new WebElementControl(locator);
        controller.label = label;
        return controller;        
    }

    public void click() {
        WebElementHelper.click(controlRootLocator);
    }

    public void waitForVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(driver -> {
           try{
               return DriverProvider.getDriver().findElement(controlRootLocator).isDisplayed();
           }catch (Exception e){
               return false;
           }
        });
        //wait.until(ExpectedConditions.visibilityOfElementLocated(controlRootLocator));
    }

    public boolean isVisible() {
        return driver.findElement(controlRootLocator).isDisplayed();
    }
}
