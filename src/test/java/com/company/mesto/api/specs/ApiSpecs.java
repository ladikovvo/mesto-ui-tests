package com.company.mesto.api.specs;

import com.company.mesto.api.config.ApiConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.oauth2;

public final class ApiSpecs {
    private ApiSpecs() {}

    public static RequestSpecification authorized(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(ApiConfig.BASE_URI)
                .setBasePath(ApiConfig.BASE_PATH)
                .setAuth(oauth2(token))
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }

    public static RequestSpecification noAuth() {
        return new RequestSpecBuilder()
                .setBaseUri(ApiConfig.BASE_URI)
                .setBasePath(ApiConfig.BASE_PATH)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }



    public static ResponseSpecification ok200() {
        return new ResponseSpecBuilder().expectStatusCode(200).build();
    }

    public static ResponseSpecification created201() {
        return new ResponseSpecBuilder().expectStatusCode(201).build();
    }

    public static ResponseSpecification status401() {
        return new ResponseSpecBuilder().expectStatusCode(401).build();
    }
}
