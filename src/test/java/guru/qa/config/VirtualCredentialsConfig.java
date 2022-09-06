package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/virtualDevice.properties")
public interface VirtualCredentialsConfig extends Config {
    @Key("platformName")
    String platformName();

    @Key("deviceName")
    String deviceName();

    @Key("platformVersion")
    String platformVersion();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

    @Key("hostURL")
    String hostURL();

    @Key("appURL")
    String appURL();

    @Key("appPath")
    String appPath();
}