package com.company.mesto.api.tests;

import com.company.mesto.api.clients.AuthClient;
import com.company.mesto.api.clients.CardsClient;
import com.company.mesto.api.clients.UsersClient;
import com.company.mesto.api.specs.ApiSpecs;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

public abstract class ApiTestBase {

    // Specs
    protected static RequestSpecification req;
    protected static RequestSpecification reqNoAuth;
    protected static RequestSpecification invalidAuth;
    protected static ResponseSpecification ok200;
    protected static ResponseSpecification created201;
    protected static ResponseSpecification status401;
    protected static ResponseSpecification status400;
    protected static ResponseSpecification status404;

    // Clients
    protected static CardsClient cardsClient;
    protected static UsersClient usersClient;


    @BeforeAll
    static void setup() {
        enableLoggingOfRequestAndResponseIfValidationFails();

        String token = new AuthClient().loginAndGetToken(); // см. ниже

        req = ApiSpecs.authorized(token);
        reqNoAuth = ApiSpecs.noAuth();
        invalidAuth = ApiSpecs.authorized("123");
        ok200 = ApiSpecs.ok200();
        created201 = ApiSpecs.created201();
        status401 = ApiSpecs.status401();
        status400 = ApiSpecs.status400();
        status404 = ApiSpecs.status404();

        cardsClient = new CardsClient(req);
        usersClient = new UsersClient(req);
    }
}
