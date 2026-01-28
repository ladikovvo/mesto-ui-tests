package com.company.mesto.testdata;

import java.util.UUID;

public final class CommonTestData {
    private CommonTestData() {
    }

    public static final String EMAIL =
            System.getProperty("TEST_EMAIL",
                    System.getenv().getOrDefault("TEST_EMAIL", "jsllv@mail.ru"));

    public static final String PASSWORD =
            System.getProperty("TEST_PASSWORD",
                    System.getenv().getOrDefault("TEST_PASSWORD", "3329"));

    public static final String POST_LINK =
            "https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg";

    public static String randomString() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    public static String randomEmail() {
        return "user_" + UUID.randomUUID().toString().replace("-", "") + "@test.com";
    }

    public static String randomPassword() {
        // 12+ символов, буквы+цифры
        return "P@" + UUID.randomUUID().toString().replace("-", "").substring(0, 14) + "1";
    }
}