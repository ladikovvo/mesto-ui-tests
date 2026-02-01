package com.company.mesto.ui.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.company.mesto.config.TestConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class UiConfig {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = TestConfig.BASE_URL;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 12000;          // ожидания shouldBe/shouldHave
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
        Configuration.reportsFolder = "target/selenide-reports";
        SelenideLogger.removeListener("AllureSelenide");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
