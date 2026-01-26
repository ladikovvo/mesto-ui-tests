package com.company.mesto.api.clients;

import com.company.mesto.api.config.ApiConfig;
import io.restassured.http.ContentType;

import java.io.File;

import static io.restassured.RestAssured.*;

public class AuthClient {
    public String loginAndGetToken(){
        File json = new File("src/test/resources/payloads/auth/login.json");

        return given().baseUri(ApiConfig.BASE_URI)
                .basePath(ApiConfig.BASE_PATH)
                .contentType(ContentType.JSON)
                .body(json)
                .when().post("/signin")
                .then().statusCode(200)
                .extract().path("token");
    }
}
