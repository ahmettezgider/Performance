package de.performance.controls;

import de.performance.tdexpressions.ExpressionEvaluator;
import de.performance.tdexpressions.InvalidExpressionException;
import de.performance.webdriver.DriverProvider;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.logging.Logger;

public class TextfieldControl extends WebControl {
    private final static Logger log = Logger.getLogger(TextfieldControl.class.getName());
    private final static String TEXT_XPATH = "//div[contains(@class, 'oxd-input-group') and normalize-space(.)=\"{0}\"]//input";

    public TextfieldControl(By locator) {
        super(locator);
    }

    public static TextfieldControl fromLabel(String label) throws InvalidExpressionException {
        return fromLabel(label, 1);
    }

    public static TextfieldControl fromLabel(String label, int no) throws InvalidExpressionException {
        label = ExpressionEvaluator.evaluate(label);
        String xpath = "((" +
                MessageFormat.format(TextfieldControl.TEXT_XPATH, label) +
                "))[" + no + "]";

        By textFieldLocator = By.xpath(xpath);
        TextfieldControl control = new TextfieldControl(textFieldLocator);
        control.label = label;
        return control;
    }


    public String getLabelText() {
        log.info(MessageFormat.format("Get value of field \"{0}\"", label));
        WebElement rootElement = getRootElement();
        log.info("... done");
        return rootElement.getText();
    }

    public void setText(String text) throws InvalidExpressionException {
        text = ExpressionEvaluator.evaluate(text);
        setText(text, true);
    }

    public void click() throws InvalidExpressionException {
        WebElement rootElement = getRootElement();
        rootElement.click();
    }

    public void setText(String text, boolean clear) throws InvalidExpressionException {
        WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(30), Duration.ofMillis(100));
        text = ExpressionEvaluator.evaluate(text);
        log.info(MessageFormat.format("Set field \"{0}\" to \"{1}\" (Löschen: {2})", label, text, "" + clear));
        WebElement rootElement = getRootElement();
        if (clear) {
            wait.until(driver->{
                try{
                    return wait.until(ExpectedConditions.and(
                            ExpectedConditions.not(ExpectedConditions.attributeContains(rootElement, "readonly", "readonly")),
                            ExpectedConditions.elementToBeClickable(rootElement)
                    ));
                }catch (Exception e){
                    return false;
                }
            });
            rootElement.clear();
        }
        WebElementHelper.click(this.controlRootLocator);
        rootElement.sendKeys(text);
        log.info("... done");
    }


    public String getText() {
        log.info(MessageFormat.format("Get value of field \"{0}\"", label));
        WebElement rootElement = getRootElement();
        log.info("... done");
        String value = rootElement.getAttribute("value");
        if (value == null)
            value = rootElement.getAttribute("data-value");
        return value;
    }


}
