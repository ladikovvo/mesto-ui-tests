package com.company.mesto.config;

public final class TestConfig {
    private TestConfig() {}

    public static final String BASE_URL =
            System.getProperty("baseUrl",
                    System.getenv().getOrDefault("BASE_URL", "https://qa-mesto.praktikum-services.ru"));

    public static final String API_BASE_PATH = "/api";
}
