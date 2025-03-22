package de.performance.stepdefs;

import de.performance.actionwords.ApplicationActionWord;
import de.performance.actionwords.TextfieldActionWords;
import de.performance.actionwords.WebElementActionWords;
import de.performance.controls.ButtonControl;
import de.performance.tdexpressions.InvalidExpressionException;
import de.performance.util.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.logging.Logger;

public class StepDefs {
    @Given("start the application")
    public void startApp() throws IOException {
        new ApplicationActionWord().startApp();
    }


    @When("user login")
    public void login() throws Exception {
        ApplicationActionWord.login();
    }

    @When("user clicks the button {string}")
    public void clickButton(String label) throws InvalidExpressionException {
        ButtonControl.fromLabel(label).click();
    }

    @When("write the text {string} to the Text field {string}")
    public void enterValueToField(String value, String name) throws InvalidExpressionException {
        TextfieldActionWords.setText(name, value);
    }

    @When("write the text {string} to the {int}.th Text field {string}")
    public void enterValueToFieldNumber(String value, int no, String name) throws InvalidExpressionException {
        TextfieldActionWords.setText(name, value, no);
    }

    @Then("the Text {string} should be visible")
    public void thTextIsShown(String text) throws InvalidExpressionException{
        WebElementActionWords.elementIsDisplayed(text, 1);
    }

    @Then("the {int}. Text {string} should be visible")
    public void nThTextIsShown(int n, String text) throws InvalidExpressionException{
        WebElementActionWords.elementIsDisplayed(text, n);
    }

    @Then("wait {int} seconds")
    public void wait(int seconds) {
        Utils.sleep(seconds*1000L);
    }


}
