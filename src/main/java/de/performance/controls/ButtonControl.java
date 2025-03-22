package de.performance.controls;

import de.performance.tdexpressions.ExpressionEvaluator;
import de.performance.tdexpressions.InvalidExpressionException;
import de.performance.util.TestLogger;
import de.performance.util.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.logging.Logger;

public class ButtonControl extends WebControl {

    private final static String BUTTON_XPATH = "//button[normalize-space(.)=\"{0}\"]";
    private final static String LINK_XPATH = "//a[normalize-space(.)=\"{0}\"]";

    private final String CLASS = this.getClass().getName();

    public ButtonControl(By locator) {
        super(locator);
    }

    public static ButtonControl fromLabel(String label) throws InvalidExpressionException {
        label = ExpressionEvaluator.evaluate(label);
        String buttonXpath = MessageFormat.format(BUTTON_XPATH, label);
        String linkXpath = MessageFormat.format(LINK_XPATH, label);
        String xpath = "(" + buttonXpath + ") | (" + linkXpath + ")";
        By locator = By.xpath(xpath);
        ButtonControl control = new ButtonControl(locator);
        control.label = label;
        return control;
    }

    public void click() {
        TestLogger.logWrite(CLASS + ", Click Button");
        WebElementHelper.click(controlRootLocator);
        TestLogger.logWrite(CLASS + ", Click Button done");
    }

    public boolean isVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(this.controlRootLocator));
            TestLogger.logWrite(CLASS + ", Button is visible");
            return true;
        } catch(Exception e) {
            return false;
        }
    }

}
