package com.natalya.challenge.covenant.pageobject.blocks;

import java.util.List;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomSelect extends PageBlock {

    public CustomSelect(WebElement element) {
        super(element);
    }

    @FindBy(xpath = "//button[@data-id='DotNetVersion']")
    private WebElement button;

    @FindBy(xpath = "//div[@class='inner show']")
    private WebElement optionsArea;

    @FindBy(xpath = "//ul//span")
    private List<WebElement> options;
    public void selectByText(String text) {
        actionBot.click(button);
        waitBot.waitElementDisplayed(optionsArea);
        for (WebElement option : options) {
            if (option.getText().equals(text)) {
                option.click();
                return;
            }
        }
        throw new NoSuchElementException(
                "Cannot locate option with text: " + text + ". Available options: " + options.stream()
                        .map(WebElement::getText).collect(
                                Collectors.toList()));
    }

    public WebElement getWrappedElement() {
        return this.element;
    }
}
