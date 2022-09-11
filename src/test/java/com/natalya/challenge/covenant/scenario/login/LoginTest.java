package com.natalya.challenge.covenant.scenario.login;

import com.natalya.challenge.covenant.domain.User;
import com.natalya.challenge.covenant.scenario.BaseUiTest;
import com.natalya.challenge.covenant.step.login.LoginService;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseUiTest {

    @Test
    public void can_create_user_and_login() {
        var loginService = new LoginService(testConfig.getBaseUrl());
        Assert.assertEquals(loginService.loginAsUser(new User("admin", "admin")), "admin");

        var newUser = new User(RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5));
        loginService.createUser(newUser);
        loginService.logout();
        Assert.assertEquals(loginService.loginAsUser(newUser), newUser.getUsername());
    }
}
