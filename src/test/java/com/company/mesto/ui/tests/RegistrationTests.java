package com.company.mesto.ui.tests;

import com.company.mesto.testdata.CommonTestData;
import com.company.mesto.ui.config.UiConfig;
import com.company.mesto.ui.pages.LoginPage;
import com.company.mesto.ui.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("ui")
@DisplayName("Registration page tests")
public class RegistrationTests extends UiConfig {

    private RegistrationPage registrationPage;

    @BeforeEach
    void openRegistrationPage(){
        registrationPage = new LoginPage()
                .openPage()
                .clickRegistration()
                ;
    }

    @Tag("auth")
    @Test
    @DisplayName("Register new valid user")
    void registerNewValidUser(){
                registrationPage
                        .registerNewUser(CommonTestData.randomEmail(), CommonTestData.randomPassword())
                        .shouldBeOpened()
                        .shouldShowRegistrationMessage("Вы успешно зарегистрировались");

    }



    @Test
    @DisplayName("Close registration message")
    void closeRegistrationMessage(){
        registrationPage
                .registerNewUser(CommonTestData.randomEmail(), CommonTestData.randomPassword())
                .closeRegistrationStatusMessage()
                .shouldBeOpened();
    }



}
