package com.natalya.challenge.covenant.step.user;

import com.natalya.challenge.covenant.domain.User;
import com.natalya.challenge.covenant.pageobject.DashBoardPage;

public class UserService {

    public void createUser(User user) {
        new DashBoardPage().navigation()
                .openUsersViewPage()
                .clickCreateButton()
                .enterUserName(user.getUsername())
                .enterPassword(user.getPassword())
                .enterConfirmPassword(user.getPassword())
                .clickCreateButton()
                .waitForReadiness();
    }

}
