package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.drivers.BrowserstackMobileDriver;
import guru.qa.drivers.PersonalMobileDriver;
import guru.qa.drivers.VirtualMobileDriver;
import guru.qa.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static guru.qa.helpers.Attach.*;
import static io.qameta.allure.Allure.step;

public class TestBase {
    @BeforeAll
    public static void setup() {
        switch (System.getProperty("deviceHost")) {
            case ("browserstackDevice"):
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case ("personalDevice"):
                Configuration.browser = PersonalMobileDriver.class.getName();
                break;
            case ("virtualDevice"):
                Configuration.browser = VirtualMobileDriver.class.getName();
                break;
        }

        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        addListener("AllureSelenide", new AllureSelenide());

        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = sessionId();
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        step("Close driver", Selenide::closeWebDriver);
        if (System.getProperty("deviceHost").equals("browserstack"))
            Attach.video(sessionId);
    }
}
