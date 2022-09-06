package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/browserstack.properties")
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

    @Key("project")
    String project();

    @Key("build")
    String build();

    @Key("name")
    String name();

    @Key("URL")
    String URL();
}