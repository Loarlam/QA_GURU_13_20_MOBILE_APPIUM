package guru.qa.helpers;

import org.aeonbits.owner.ConfigFactory;
import guru.qa.config.BrowserstackCredentialsConfig;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {
    static BrowserstackCredentialsConfig credentialsConfig = ConfigFactory.create(BrowserstackCredentialsConfig.class);

    public static String videoUrl(String sessionId) {
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(credentialsConfig.user(), credentialsConfig.key())
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
