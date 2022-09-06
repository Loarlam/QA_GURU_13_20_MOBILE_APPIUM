package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/browserstackDevice.properties")
public interface BrowserstackCredentialsConfig extends Config {
    @Key("user")
    String user();

    @Key("key")
    String key();

    @Key("app")
    String app();

    @Key("device")
    String device();

    @Key("os_version")
    String os_version();

    @Key("hostURL")
    String hostURL();
}