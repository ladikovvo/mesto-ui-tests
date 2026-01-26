package com.company.mesto.api.clients;

import com.company.mesto.api.models.Card;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CardsClient {
    private final RequestSpecification req;

    public CardsClient(RequestSpecification req) {
        this.req = req;
    }



    public String createCardAndGetId(String name, String link){
        Card card = new Card(name, link);

        return given().spec(req)
                .body(card)
                .when()
                .post("/cards")
                .then()
                .statusCode(201)
                .body("data._id", notNullValue())
                .body("data.link", equalTo(link))
                .body("data.name", equalTo(name))
                .extract().path("data._id");
    }

    public List<String> createCardExpecting400_returnKeys(String name, String link){
        Card card = new Card(name, link);

        return given().spec(req)
                .body(card)
                .when()
                .post("/cards")
                .then()
                .statusCode(400)
                .body("message", not(emptyOrNullString()))
                .extract().path("validation.keys");
    }

//    public String getPostNameById(){
//    }

//    public String getPostLinkById(){
//    }



    public void deleteCard(String id){
        given().spec(req)
                .when()
                .delete("/cards/{id}", id)
                .then()
                .statusCode(200);
    }

    public List<String> getAllCardIds(){
        return given().spec(req)
                .when()
                .get("/cards")
                .then()
                .statusCode(200)
                .extract().path("data._id");
    }

    public void shouldNotContainCardId(String id){
        List<String> ids = getAllCardIds();
        assertFalse(ids.contains(id),  "Expected card id to be absent in /cards list, but it was found: " + id);
    }


}
