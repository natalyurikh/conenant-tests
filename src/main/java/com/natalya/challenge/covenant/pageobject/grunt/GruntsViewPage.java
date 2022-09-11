package com.natalya.challenge.covenant.pageobject.grunt;

import com.natalya.challenge.covenant.pageobject.BasePage;
import com.natalya.challenge.covenant.pageobject.blocks.Table;
import java.nio.file.WatchEvent;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GruntsViewPage extends BasePage {

    @FindBy(css = ".table-responsive")
    private WebElement gruntsTable;

    private Table getGruntsTable() {
        waitBot.waitElementDisplayed(gruntsTable);
        return new Table(gruntsTable);
    }

    public List<Map<String, String>> getGruntList() {
        return getGruntsTable().readLinesAsMap();
    }

    @Override
    public void waitForReadiness() {
        waitBot.waitElementDisplayed(gruntsTable);
    }
}
