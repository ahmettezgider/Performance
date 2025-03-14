package de.performance.actionwords;


import de.performance.controls.ButtonControl;
import de.performance.tdexpressions.InvalidExpressionException;

public class ButtonActionWords {

    public static void click(String label) throws InvalidExpressionException {
        ButtonControl.fromLabel(label)
                .click();
    }


}
