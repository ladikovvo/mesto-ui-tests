package com.company.mesto.api.clients;

import com.company.mesto.api.config.ApiConfig;
import io.restassured.http.ContentType;

import java.io.File;
import java.io.InputStream;

import static io.restassured.RestAssured.*;

public class AuthClient {
    public String loginAndGetToken(){
        InputStream is = getClass().getClassLoader().getResourceAsStream("payloads/auth/login.json");
        if (is == null) throw new IllegalStateException("login.json not found in resources");

        return given()
                .baseUri(ApiConfig.BASE_URI)
                .basePath(ApiConfig.BASE_PATH)
                .contentType(ContentType.JSON)
                .body(is)
                .when().post("/signin")
                .then().statusCode(200)
                .extract().path("token");
    }
}
