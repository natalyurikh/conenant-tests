package com.natalya.challenge.covenant.step.launcher;

import com.natalya.challenge.covenant.core.bot.WaitBot;
import com.natalya.challenge.covenant.core.driver.TestConfig;
import com.natalya.challenge.covenant.domain.launcher.InstallUtilLauncher;
import com.natalya.challenge.covenant.pageobject.DashBoardPage;
import com.natalya.challenge.covenant.pageobject.launcher.InstallUtilLauncherCreatePage;
import com.natalya.challenge.covenant.util.FileUtils;
import java.io.File;
import java.nio.file.FileSystemException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.ui.ExpectedCondition;

@RequiredArgsConstructor
@Log4j2
public class LauncherService {

    private final TestConfig testConfig;

    public File createInstallUtilLauncher(InstallUtilLauncher launcher) throws FileSystemException {
        var lastFile = FileUtils.getLastModifiedFile(testConfig.getBrowserDownloadPath());
        String fileName = (null == lastFile) ? "" : lastFile.getName();

        new DashBoardPage().navigation().openLaunchersViewPage().createLauncherType("InstallUtil");
        var page = new InstallUtilLauncherCreatePage();
        page.waitForReadiness();
        page.selectListener(launcher.getListener())
                .selectTemplate(launcher.getImplantTemplate())
                .selectDotNetVersion(launcher.getDotNetVersion())
                .selectValidateCert(StringUtils.capitalize(String.valueOf(launcher.isValidateCert())))
                .selectUseCertPinning(StringUtils.capitalize(String.valueOf(launcher.isUseCertPinning())))
                .enterDelay(launcher.getDelay())
                .enterJitterPercent(launcher.getJitterPercent())
                .enterConnectAttempts(launcher.getConnectAttempts())
                .enterKillDate(launcher.getKillDateInput())
                .clickGenerate()
                .clickDownload();

        WaitBot.getBot().waitForCondition((ExpectedCondition<Boolean>) driver -> {
            var downloadedFile = FileUtils.getLastModifiedFile(testConfig.getBrowserDownloadPath());
            if (null == downloadedFile)
                return false;
            return !fileName.equals(downloadedFile.getName());
        });

        var downloadedFile = FileUtils.getLastModifiedFile(testConfig.getBrowserDownloadPath());
        //workaround for unconfirmed download
        return FileUtils.renameFile(downloadedFile, "Launcher_" + RandomStringUtils.randomAlphabetic(3) + ".dll");
    }
}
