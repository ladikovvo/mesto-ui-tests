package com.company.mesto.api.tests;

import com.company.mesto.api.clients.CardsClient;
import com.company.mesto.api.clients.UsersClient;
import com.company.mesto.api.data.TestData;
import com.company.mesto.api.models.UserMe;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Tag("api")
public class MestoApiLesson1Tests extends ApiTestBase {



    @Test
    void checkUserName() {
        given().spec(req)
                .get("/users/me")
                .then().spec(ok200)
                .assertThat().body("data.name", notNullValue());
    }

    @Test
    void addNewCard_andDeleteIt(){
        CardsClient cardsClient = new CardsClient(req);
        String postName = TestData.randomString();

        String id = cardsClient.createCardAndGetId(postName , TestData.POST_LINK);
        cardsClient.deleteCard(id);
        cardsClient.shouldNotContainCardId(id);
    }

    @Test
    void updateProfile_withRollback(){
        UsersClient usersClient = new UsersClient(req);

        UserMe me = usersClient.getMe();
        String oldName = me.getName();
        String oldAbout = me.getAbout();

        String newName = TestData.randomString();
        String newAbout = TestData.randomString();

        try{
            usersClient.updateMe(newName, newAbout);
            usersClient.shouldHaveProfile(newName, newAbout);

            assertNotEquals(oldName, newName);

        } finally {
            usersClient.updateMe(oldName, oldAbout);
            usersClient.shouldHaveProfile(oldName, oldAbout);
        }

    }

    @Test
    void reqInvalidAuth(){
        given().spec(invalidAuth)
                .when()
                .get("/users/me")
                .then()
                .spec(status401)
                .body("message", not(emptyOrNullString()));
    }


    static Stream<Arguments> createCardCases(){
        return Stream.of(
                Arguments.of("", TestData.POST_LINK, "name"),
                Arguments.of(TestData.randomString(), "", "link"),
                Arguments.of("", "", "name")
        );
    }

    @ParameterizedTest(name = "[{index}] name=({0}), link=({1}), key={2}")
    @MethodSource("createCardCases")
    void reqInvalidNewCard(String name, String link, String key){
        CardsClient cardsClient = new CardsClient(req);
        List<String> keys = cardsClient.createCardExpecting400_returnKeys(name, link);
        assertTrue(keys.contains(key));
        assertFalse(keys.isEmpty());  // если validation.keys исчезнет
    }








}
