package com.company.mesto.api.clients;

import com.company.mesto.api.models.ApiResponse;
import com.company.mesto.api.models.UpdateProfileRequest;
import com.company.mesto.api.models.UserMe;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersClient {
    private final RequestSpecification req;

    public UsersClient(RequestSpecification req) {
        this.req = req;
    }



    @Step("Get user Me")
    public UserMe getMe(){
        ApiResponse<UserMe> parsed = given().spec(req)
                .when()
                .get("/users/me")
                .then()
                .statusCode(200)
                .extract().as(new TypeRef<ApiResponse<UserMe>>() {});
        return parsed.getData();
    }


    @Step("Update user Me to name={name}, about={about}")
    public void updateMe(String name, String about) {
        UpdateProfileRequest body = new UpdateProfileRequest(name, about);


        given().spec(req)
                .body(body)
                .when()
                .patch("/users/me")
                .then()
                .statusCode(200)
                .body("data.name", equalTo(name))
                .body("data.about", equalTo(about));
    }

    @Step("Update profile expect 400 And return validations keys")
    public List<String> updateProfileExpect400_returnValidationKeys(String name, String about) {
        UpdateProfileRequest body = new UpdateProfileRequest(name, about);

        return given().spec(req)
                .body(body)
                .when()
                .patch("/users/me")
                .then()
                .statusCode(400)
                .body("message", not(emptyOrNullString()))
                .body("validation.keys", not(empty()))
                .extract().path("validation.keys");
    }

    @Step("Should have profile name={name}, about={about}")
    public void shouldHaveProfile(String name, String about) {
        UserMe me = getMe();
        assertEquals(name, me.getName());
        assertEquals(about, me.getAbout());
    }


}
