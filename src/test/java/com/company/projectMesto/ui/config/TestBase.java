package com.company.projectMesto.ui.config;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://qa-mesto.praktikum-services.ru";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;          // ожидания shouldBe/shouldHave
        Configuration.headless = false;        // true для CI
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
