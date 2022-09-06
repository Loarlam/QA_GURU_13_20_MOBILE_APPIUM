package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/personalDevice.properties")
public interface PersonalCredentialsConfig extends Config {
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

    @Key("URL")
    String URL();

    @Key("appURL")
    String appURL();

    @Key("appPath")
    String appPath();
}