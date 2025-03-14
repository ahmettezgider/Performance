package de.performance.actionwords;

import de.performance.controls.TextfieldControl;
import de.performance.tdexpressions.ExpressionEvaluator;
import de.performance.tdexpressions.InvalidExpressionException;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.util.List;

public class TextfieldActionWords {

    public static void setText(String label, String value, int no) throws InvalidExpressionException {
        TextfieldControl
                .fromLabel(label, no)
                .setText(value);
    }

    public static void setText(String label, String value) throws InvalidExpressionException {
        TextfieldControl
                .fromLabel(label)
                .setText(value);
    }

    public static void setTextAndPressTab(String label, String value) throws InvalidExpressionException {
        TextfieldControl
                .fromLabel(label)
                .setText(value + Keys.TAB);
    }

    public static void setTextAndPressEnter(String label, String value) throws InvalidExpressionException {
        TextfieldControl
                .fromLabel(label)
                .setText(value + Keys.ENTER);
    }

    public static void checkText(String label, String expectedValue) throws InvalidExpressionException {
        String actualValue = TextfieldControl
                .fromLabel(label)
                .getText();
        expectedValue = ExpressionEvaluator.evaluate(expectedValue);
        Assert.assertEquals(expectedValue, actualValue);
    }

}
