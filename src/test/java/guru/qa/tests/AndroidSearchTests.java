package guru.qa.tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AndroidSearchTests extends TestBase {
    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Checking \"saved\" button in mobile app Wikipedia")
    void savedButtonTest() {
//        switchTo().alert().accept();
//        back();

        step("Pressing \"saved\" button", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_reading_lists")).click());
        step("Checking if the correct page is open", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/messageTitleView")).shouldHave(text("Sync reading lists")));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Checking \"search\" button in mobile app Wikipedia")
    void searchButtonTest() {
//        switchTo().alert().accept();
//        back();

        step("Pressing \"search\" button", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_search")).click());
        step("Checking if the correct page is open", () ->
        {
            $(AppiumBy.id("org.wikipedia.alpha:id/history_title")).shouldHave(text("History"));
            $(AppiumBy.id("org.wikipedia.alpha:id/history_empty_title")).shouldHave(text("No recently viewed articles"));
            $(AppiumBy.id("org.wikipedia.alpha:id/history_empty_message")).shouldHave(text("Track what you've been reading here."));
        });
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Pressing \"edits\" button")
    void editsButtonTest() {
//        switchTo().alert().accept();
//        back();

        step("Checking \"edits\" button in mobile app Wikipedia", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_edits")).click());
        step("Checking if the correct page is open", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/messageTitleView")).shouldHave(text("Did you know that everyone can edit Wikipedia?")));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Clicking in the \"search wikipedia\" field and search \"dyskinesia\"")
    void searchTextWikiInSearchSrc() {
//        back();
//        switchTo().alert().accept();
        step("Clicking in the \"search wikipedia\" field", () ->
                $(AppiumBy.accessibilityId("Search Wikipedia")).click());
        step("Entering a value \"dyskinesia\" in the search wikipedia field", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))).sendKeys("Dyskinesia");
        step("Checking found content for \"dyskinesia\"", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Owner("Loarlam")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Clicking in the \"search wikipedia\" field and search \"aplasia\"")
    void searchTextWikiInSearchContatiner() {
//        back();
//        switchTo().alert().accept();
        step("Clicking in the \"search wikipedia\" field", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click());
        step("Entering a value \"aplasia\" in the search wikipedia field", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))).sendKeys("Aplasia");
        step("Checking found content for \"aplasia\"", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/search_results_list"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
        step("Opening \"aplasia\" page", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click());
    }
}
