package guru.qa.drivers;

import com.codeborne.selenide.WebDriverProvider;
import guru.qa.config.VirtualCredentialsConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class VirtualMobileDriver implements WebDriverProvider {
    static VirtualCredentialsConfig credentialsConfig = ConfigFactory.create(VirtualCredentialsConfig.class);

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        File app = getApp();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName(credentialsConfig.platformName());
        options.setDeviceName(credentialsConfig.deviceName());
        options.setPlatformVersion(credentialsConfig.platformVersion());
        options.setApp(app.getAbsolutePath());
        options.setAppPackage(credentialsConfig.appPackage());
        options.setAppActivity(credentialsConfig.appActivity());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    private File getApp() {
        String appUrl = credentialsConfig.appURL();
        String appPath = credentialsConfig.appPath();

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app;
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL(credentialsConfig.hostURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
