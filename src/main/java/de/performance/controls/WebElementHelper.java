package de.performance.controls;

import de.performance.util.TestLogger;
import de.performance.webdriver.DriverProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Logger;

public class WebElementHelper {

    private final static Duration DEFAULT_WAIT_TIME = Duration.ofSeconds(60);

    public static WebElement waitForControl(By locator) {
        final long start = System.currentTimeMillis();

        WebDriver driver = DriverProvider.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));

        return element;
    }


    public static WebElement waitForControlElement(WebElement control, By locator) {
        final long start = System.currentTimeMillis();
        WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), DEFAULT_WAIT_TIME);
        WebElement element = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(control, locator));
        return element;
    }
     

    public static void clear(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), DEFAULT_WAIT_TIME);
        wait.until((driver) -> {
            try {
                WebElement elem = driver.findElement(locator);
                elem.clear();
                return true;
            } catch (InvalidElementStateException | NoSuchElementException ice) {
                return false;
            }
        });
    }

    public static void click(By locator) {
        final long start = System.currentTimeMillis();

        WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), DEFAULT_WAIT_TIME, Duration.ofMillis(20));
        WebElement element = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));        
        wait.until(ExpectedConditions.elementToBeClickable(element));        
        try {
            element.click();
        } catch(ElementClickInterceptedException | StaleElementReferenceException e) {
            long startInterceptedOrStale = System.currentTimeMillis();
            wait.until((driver) -> {
                try {
                    WebElement element2 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));        
                    wait.until(ExpectedConditions.elementToBeClickable(element2));        
                    element2.click();
                    return true;
                } catch (ElementClickInterceptedException ice) {
                    TestLogger.logWrite("intercepted");
                    TestLogger.logWrite(ice.getMessage());
                    return false;
                } catch (StaleElementReferenceException stale) {
                    TestLogger.logWrite("stale");
                    return false;
                }
            });
            long endInterceptedOrStale = System.currentTimeMillis();
        }

    }

    public static void click(final WebElement rootElement, By locator) {
        final long start = System.currentTimeMillis();
        WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), DEFAULT_WAIT_TIME);
        wait.until((driver) -> {
            try {
                WebElement elem = rootElement.findElement(locator);
                if (elem.isDisplayed() && elem.isEnabled()) {
                    elem.click();
                    return true;
                }
                return false;
            } catch (ElementClickInterceptedException | StaleElementReferenceException ice) {
                return false;
            }
        });
    }


}
