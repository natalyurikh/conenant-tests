package com.natalya.challenge.covenant.step.login;

import com.natalya.challenge.covenant.domain.User;
import com.natalya.challenge.covenant.pageobject.DashBoardPage;
import com.natalya.challenge.covenant.pageobject.LoginPage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginService {
    private final String baseUrl;

    public void createUser(User user) {
        new DashBoardPage().navigation().openUsersViewPage().clickCreateButton()
                .enterUserName(user.getUsername())
                .enterPassword(user.getPassword())
                .enterConfirmPassword(user.getPassword())
                .clickCreateButton()
                .waitForReadiness();
    }

    /**
     * Login us user
     * @param user user to login
     * @return logged username
     */

    public String loginAsUser(User user) {
        var dashBoardPage = new LoginPage()
                .openLoginPage(baseUrl)
                .typeUserName(user.getUsername())
                .typePassword(user.getPassword())
                .clickLoginButton();
        dashBoardPage.waitForReadiness();
        return dashBoardPage.header().getUserName();
    }

    public void logout() {
        new DashBoardPage().header().clickLogout();
    }

    public void reLoginAsUser(User user) {
        logout();
        loginAsUser(user);
    }
}
