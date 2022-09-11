package com.natalya.challenge.covenant.pageobject.blocks;

import com.natalya.challenge.covenant.pageobject.grunt.GruntsViewPage;
import com.natalya.challenge.covenant.pageobject.launcher.LauncherViewPage;
import com.natalya.challenge.covenant.pageobject.listener.ListenersViewPage;
import com.natalya.challenge.covenant.pageobject.user.UsersViewPage;
import lombok.NonNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GlobalNavBlock extends PageBlock {

    public GlobalNavBlock(@NonNull WebElement element) {
        super(element);
    }

    @FindBy(id = "nav-dashboard")
    private WebElement dashboardLink;
    @FindBy(id = "nav-listeners")
    private WebElement listenersLink;
    @FindBy(id = "nav-launchers")
    private WebElement launcherLink;
    @FindBy(id = "nav-grunts")
    private WebElement gruntsLink;
    @FindBy(id = "nav-templates")
    private WebElement templatesLink;
    @FindBy(id = "nav-tasks")
    private WebElement tasksLink;
    @FindBy(id = "nav-taskings")
    private WebElement taskingsLink;
    @FindBy(id = "nav-graphs")
    private WebElement graphsLink;
    @FindBy(id = "nav-data")
    private WebElement dataLink;
    @FindBy(id = "nav-users")
    private WebElement usersLink;

    public ListenersViewPage openListenersViewPage() {
        actionBot.click(listenersLink);
        return new ListenersViewPage();
    }

    public LauncherViewPage openLaunchersViewPage() {
        actionBot.click(launcherLink);
        return new LauncherViewPage();
    }

    public UsersViewPage openUsersViewPage() {
        actionBot.click(usersLink);
        return new UsersViewPage();
    }

    public GruntsViewPage openGruntsViewPage() {
        actionBot.click(gruntsLink);
        return new GruntsViewPage();
    }
}
