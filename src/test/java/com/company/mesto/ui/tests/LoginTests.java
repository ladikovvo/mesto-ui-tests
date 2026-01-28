package com.company.mesto.ui.tests;

import com.company.mesto.testdata.CommonTestData;
import com.company.mesto.ui.config.UiConfig;
import com.company.mesto.ui.data.UiTestData;
import com.company.mesto.ui.pages.LoginPage;
import com.company.mesto.ui.utils.Html5Validation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Tag;

import java.util.stream.Stream;

@Tag("ui")
@Tag("auth")
public class LoginTests extends UiConfig {

    LoginPage loginPage;

    @BeforeEach
    void openLoginPage() {
        loginPage = new LoginPage();
        loginPage.openPage();

    }


    @Nested
    @DisplayName("Valid credentials")
    class Valid {

        @Tag("smoke")
        @Test
        @Timeout(20)
        @DisplayName("UserData can login with valid credentials")
        void shouldLoginWithValidCredentials() {
            loginPage
                    .login(CommonTestData.EMAIL, CommonTestData.PASSWORD)
                    .shouldBeOpened()
            ;
        }
    }


    @Nested
    @DisplayName("Invalid credentials")
    class Invalid {

        static Stream<Arguments> invalidCreds(){
            return Stream.of(
                    Arguments.of(UiTestData.INVALID_EMAIL, UiTestData.INVALID_PASSWORD),
                    Arguments.of(CommonTestData.EMAIL, UiTestData.INVALID_PASSWORD),
                    Arguments.of(UiTestData.INVALID_EMAIL, CommonTestData.PASSWORD)
            );
        }

        @ParameterizedTest(name = "[{index}] email={0}, pass={1}")
        @MethodSource("invalidCreds")
        @DisplayName("Shows error popup for invalid credentials")
        void shouldShowErrorForInvalidCredential(String email, String pass) {
            loginPage
                    .fillCredential(email, pass)
                    .clickSignIn()
                    .shouldShowError("Что-то пошло не так!", "Попробуйте ещё раз.")
            ;
        }

        @Test
        @DisplayName("Can close error popup")
        void shouldCloseErrorPopup() {
            loginPage
                    .fillCredential(UiTestData.INVALID_EMAIL, UiTestData.INVALID_PASSWORD)
                    .clickSignIn()
                    .closeErrorPopup();
        }
    }

    @Nested
    @DisplayName("HTML5 validation")
    class Html5ValidationGroup {

        @Test
        @DisplayName("Empty email should be invalid")
        void shouldShowErrorForEmptyEmail() {
            loginPage
                .fillCredential("", CommonTestData.PASSWORD)
                .clickSignIn();
            Html5Validation.shouldBeInvalid(loginPage.getEmailInput());
    }

        @Test
        @DisplayName("Empty password should be invalid")
        void shouldShowErrorForEmptyPassword() {
            loginPage
                    .fillCredential(CommonTestData.EMAIL, "")
                    .clickSignIn();
            Html5Validation.shouldBeInvalid(loginPage.getPasswordInput());
    }
    }


    @Nested
    @DisplayName("Navigation")
    class Navigation {

        @Test
        void RegistrationPageShouldOpen() {
            loginPage
                    .clickRegistration()
                    .shouldBeOpened()
            ;
        }
    }




}
