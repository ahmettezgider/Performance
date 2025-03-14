package de.performance.actionwords;

import de.performance.controls.WebElementControl;
import de.performance.tdexpressions.InvalidExpressionException;

public class WebElementActionWords {
    

    public static void clickElement(String text, int no) throws InvalidExpressionException {
        WebElementControl.fromLabel(text, no).click();
    }

    public static void elementIsDisplayed(String text, int no) throws InvalidExpressionException {
        WebElementControl.fromLabel(text, no).waitForVisible();
    }    
}
