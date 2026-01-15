package com.company.projectMesto.ui.tests;

import com.company.projectMesto.ui.config.TestBase;
import com.company.projectMesto.ui.config.TestData;
import com.company.projectMesto.ui.pages.HomePage;
import com.company.projectMesto.ui.pages.LoginPage;
import com.company.projectMesto.ui.pages.RegistretionPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationTests extends TestBase {

    private RegistretionPage registretionPage;

    @BeforeEach
    void openRegistrationPage(){
        registretionPage = new LoginPage()
                .openPage()
                .clickRegistration()
                ;
    }

    @Test
    void registerNewValidUser(){
                registretionPage
                        .registerNewUser(TestData.randomEmail(), TestData.randomPassword())
                        .shouldShowRegistrationMessage("Вы успешно зарегистрировались");

    }



    @Test
    void closeRegistrationMessage(){
        registretionPage
                .registerNewUser(TestData.randomEmail(), TestData.randomPassword())
                .closeRegistrationStatusMessage()
                .shouldBeOpened();
    }



}
