package guru.qa.drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import guru.qa.config.BrowserstackCredentialsConfig;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {
    static BrowserstackCredentialsConfig credentialsConfig = ConfigFactory.create(BrowserstackCredentialsConfig.class);

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);
        mutableCapabilities.setCapability("browserstack.user", credentialsConfig.user());
        mutableCapabilities.setCapability("browserstack.key", credentialsConfig.key());
        mutableCapabilities.setCapability("app", credentialsConfig.app());
        mutableCapabilities.setCapability("device", credentialsConfig.device());
        mutableCapabilities.setCapability("os_version", credentialsConfig.os_version());

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL(credentialsConfig.hostURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
