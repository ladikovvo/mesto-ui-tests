package com.company.mesto.api.tests;

import com.company.mesto.api.data.ApiTestData;
import com.company.mesto.api.models.CreateCardRequest;
import com.company.mesto.api.models.UpdateProfileRequest;
import com.company.mesto.testdata.CommonTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

@Tag("api")
@DisplayName("Api negative tests")
public class ApiNegativeTests extends ApiTestBase {


    @DisplayName("Invalid authorisation")
    @Nested
    class InvalidAuthorisationRequests {

        @DisplayName("Should return 401: GET /users/me (invalid auth)")
        @Test
        void shouldReturn401_whenGettingUserMeWithInvalidAuth() {
            given().spec(invalidAuth)
                    .when()
                    .get("/users/me")
                    .then()
                    .spec(status401)
                    .body("message", not(emptyOrNullString()));
        }

        @DisplayName("Should return 401: POST /cards (invalid auth)")
        @Test
        void shouldReturn401_whenCreatingCardWithInvalidAuth() {
            CreateCardRequest card = new CreateCardRequest(CommonTestData.randomString(), CommonTestData.POST_LINK);

            given().spec(invalidAuth)
                    .body(card)
                    .when()
                    .post("/cards")
                    .then()
                    .spec(status401)
                    .body("message", not(emptyOrNullString()));
        }

        @DisplayName("Should return 401: PATCH /users/me (invalid auth)")
        @Test
        void shouldReturn401_whenUpdatingProfile() {
            UpdateProfileRequest updateProfileRequest =
                    new UpdateProfileRequest(CommonTestData.randomString(), CommonTestData.randomString());

            given().spec(invalidAuth)
                    .body(updateProfileRequest)
                    .when()
                    .patch("/users/me")
                    .then()
                    .spec(status401)
                    .body("message", not(emptyOrNullString()));
        }

        @DisplayName("Should return 401: DELETE /cards/{id} (invalid auth)")
        @Test
        void shouldReturn401_whenDeletingCard() {
            String id = ApiTestData.INVALID_ID;

            given().spec(invalidAuth)
                    .when()
                    .delete("/cards/{id}", id)
                    .then()
                    .spec(status401)
                    .body("message", not(emptyOrNullString()));


        }
    }


        @DisplayName("Invalid request values")
        @Nested
        class InvalidValuesRequests{

            static Stream<Arguments> createCardCases(){
                return Stream.of(
                        Arguments.of("", CommonTestData.POST_LINK, "name"),
                        Arguments.of(CommonTestData.randomString(), "", "link"),
                        Arguments.of("", "", "name")
                );
            }

            @ParameterizedTest(name = "[{index}] name=\"{0}\", link=\"{1}\", key=\"{2}\"")
            @MethodSource("createCardCases")
            @DisplayName("Should return 400: POST /cards (invalid values)")
            void shouldReturn400_whenCreatingCardWithInvalidValues(String name, String link, String key){
                List<String> keys = cardsClient.createCardExpect400_returnValidationKeys(name, link);
                assertAll("Validation keys",
                        () -> assertFalse(keys.isEmpty(), "keys should not be empty"),
                        () -> assertTrue(keys.contains(key), "expected key=" + key + ", actual=" + keys)
                );

            }


            static Stream<Arguments> updateProfileCases(){
                return Stream.of(
                        Arguments.of("", CommonTestData.randomString(), "name"),
                        Arguments.of(CommonTestData.randomString(), "", "about"),
                        Arguments.of("", "", "name")
                );
            }

            @ParameterizedTest(name = "[{index}] name=\"{0}\", about=\"{1}\", key=\"{2}\"")
            @MethodSource("updateProfileCases")
            @DisplayName("Should return 400: PATCH /users/me (invalid values)")
            void shouldReturn400_whenUpdatingProfileWithInvalidValues(String name, String about, String key){
                List<String> keys = usersClient.updateProfileExpect400_returnValidationKeys(name, about);
                assertAll("Validation keys",
                        () -> assertFalse(keys.isEmpty(), "keys should not be empty"),
                        () -> assertTrue(keys.contains(key), "expected key=" + key + ", actual=" + keys)
                        );

            }




        }

        @DisplayName("Non-existing resources")
        @Nested
        class NonExistingResourcesRequests {

            @DisplayName("Should return 404: DELETE /cards/{id} (non-existing id)")
            @Test
            void shouldReturn404_whenDeletingCardWithNonExistingId(){
                String id = ApiTestData.INVALID_ID;

                cardsClient.deleteCard(id)
                        .then()
                        .spec(status404)
                        .body("message", not(emptyOrNullString()));

            }

        }



    }

