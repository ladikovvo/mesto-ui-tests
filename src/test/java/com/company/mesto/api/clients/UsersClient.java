package com.company.mesto.api.clients;

import com.company.mesto.api.models.UpdateProfileRequest;
import com.company.mesto.api.models.UserMe;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UsersClient {
    private final RequestSpecification req;

    public UsersClient(RequestSpecification req) {
        this.req = req;
    }



    public UserMe getMe(){
        return given().spec(req)
                .when()
                .get("/users/me")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject("data" , UserMe.class);
    }


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

    public void shouldHaveProfile(String name, String about) {
        given().spec(req)
                .when()
                .get("/users/me")
                .then()
                .statusCode(200)
                .body("data.name", equalTo(name))
                .body("data.about", equalTo(about));
    }


}
