package com.natalya.challenge.covenant.pageobject.launcher;

import com.natalya.challenge.covenant.pageobject.BasePage;
import com.natalya.challenge.covenant.pageobject.blocks.Table;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LauncherViewPage extends BasePage {

    @FindBy(css = ".table-responsive")
    private WebElement launchersTable;

    private Table getLaunchersTable() {
        waitBot.waitElementDisplayed(launchersTable);
        return new Table(launchersTable);
    }

    public List<Map<String, String>> readLinesAsMap() {
        return getLaunchersTable().readLinesAsMap();
    }

    @Override
    public void waitForReadiness() {
        waitBot.waitElementDisplayed(launchersTable);
    }

    public void createLauncherType(String type) {
        //actionBot.navigateUrl("https://localhost:7443/launcher/create/1");
       getLaunchersTable().clickOnCellWithText(type);
    }
}
