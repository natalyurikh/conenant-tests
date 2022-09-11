package com.natalya.challenge.covenant.pageobject.blocks;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Table representative
 */
public class Table extends PageBlock {

    public Table(WebElement element) {
        super(element);
    }

    @FindBy(xpath = "//thead/tr/th/div")
    private List<WebElement> headerList;
    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> rowList;
    @FindBy(xpath = "//tbody/tr/td")
    private List<WebElement> cellList;
    private static final String SEARCH_XPATH = "//tbody/tr/td[contains(.,'%s')]";

    public List<Map<String, String>> readLinesAsMap() {
        var headers =
                headerList.stream().map(e -> e.getText().replaceAll("\n.", "")).collect(Collectors.toList());
        var cells = cellList.stream().map(WebElement::getText).collect(Collectors.toList());

        Preconditions.checkArgument(headers.size() * rowList.size() == cells.size(),
                "Header and row have different structure");

        var values = new ArrayList<Map<String, String>>();

        int i = 0;
        while (i < cells.size()) {
            var line = new LinkedHashMap<String, String>();
            for (String header : headers) {
                line.put(header, cells.get(i));
                i++;
            }
            values.add(line);
        }
        return values;
    }

    public void clickOnCellWithText(String text) {
        waitBot.waitElementPresent(By.xpath(String.format(SEARCH_XPATH, text)));
        WebElement element = driver.findElement(By.xpath(String.format(SEARCH_XPATH, text)));
        actionBot.click(element);
    }
}
