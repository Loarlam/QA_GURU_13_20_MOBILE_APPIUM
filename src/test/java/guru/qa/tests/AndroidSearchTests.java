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

@Tag("android")
public class AndroidSearchTests extends TestBase {
    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка кнопки search в мп Wikipedia")
    void savedButtonTest() {
//        switchTo().alert().accept();
        back();

        step("Нажатие на кнопку saved", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_reading_lists")).click());
        step("Проверка открытия верной страницы", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/messageTitleView")).shouldHave(text("Sync reading lists")));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка кнопки search в мп Wikipedia")
    void searchButtonTest() {
//        switchTo().alert().accept();
        back();

        step("Нажатие на кнопку search", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_search")).click());
        step("Проверка открытия верной страницы", () ->
        {
            $(AppiumBy.id("org.wikipedia.alpha:id/history_title")).shouldHave(text("History"));
            $(AppiumBy.id("org.wikipedia.alpha:id/history_empty_container")).shouldHave(text("No recently viewed articles"));
            $(AppiumBy.id("org.wikipedia.alpha:id/history_empty_message")).shouldHave(text("Track what you've been reading here."));
        });
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка кнопки search в мп Wikipedia")
    void editsButtonTest() {
//        switchTo().alert().accept();
        back();

        step("Нажатие на кнопку edits", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_edits")).click());
        step("Проверка открытия верной страницы", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/messageTitleView")).shouldHave(text("Did you know that everyone can edit Wikipedia?")));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Клик в поле search wikipedia и поиск \"dyskinesia\"")
    void searchTextWikiInSearchSrc() {
        back();
//        switchTo().alert().accept();
        step("Клик в поле search wikipedia", () ->
                $(AppiumBy.accessibilityId("Search Wikipedia")).click());
        step("Ввод в поле search wikipedia значения \"dyskinesia\"", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))).sendKeys("Dyskinesia");
        step("Проверка найденного контента \"dyskinesia\"", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Owner("Loarlam")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Клик в поле search wikipedia и поиск \"aplasia\"")
    void searchTextWikiInSearchContatiner() {
        back();
//        switchTo().alert().accept();
        step("Клик в поле search wikipedia", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click());
        step("Ввод в поле search wikipedia значения \"aplasia\"", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))).sendKeys("Aplasia");
        step("Проверка найденного контента \"aplasia\"", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/search_results_list"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
        step("Открытие страницы Aplasia", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click());
    }
}
