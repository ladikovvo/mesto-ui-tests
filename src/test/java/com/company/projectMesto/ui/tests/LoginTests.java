package com.company.projectMesto.ui.tests;

import com.company.projectMesto.ui.config.TestBase;
import com.company.projectMesto.ui.config.TestData;
import com.company.projectMesto.ui.pages.LoginPage;
import com.company.projectMesto.ui.utils.Html5Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    LoginPage loginPage ;

    @BeforeEach
    void openLoginPage(){
        loginPage = new LoginPage();
        loginPage.openPage();

    }

    @Test
    void shouldLoginWithValidCredentials(){
        loginPage
                .login(TestData.EMAIL, TestData.PASSWORD)
                .shouldBeOpened()
        ;
    }


    @Test
    void shouldShowErrorForInvalidCredential(){
        loginPage
                .fillCredential(TestData.INVALID_EMAIL, TestData.INVALID_PASSWORD)
                .clickSignIn()
                .shouldShowError("Что-то пошло не так!" , "Попробуйте ещё раз.")
        ;
    }

    @Test
    void shouldShowErrorForInvalidPassword(){
        loginPage
                .fillCredential(TestData.EMAIL, TestData.INVALID_PASSWORD)
                .clickSignIn()
                .shouldShowError("Что-то пошло не так!" , "Попробуйте ещё раз.");
    }

    @Test
    void shouldShowErrorForEmptyEmail(){
        loginPage
                .fillCredential("", TestData.PASSWORD)
                .clickSignIn();
        Html5Validation.shouldBeInvalid(loginPage.getEmailInput());
    }

    @Test
    void shouldShowErrorForEmptyPassword(){
        loginPage
                .fillCredential(TestData.EMAIL,"")
                .clickSignIn();
        Html5Validation.shouldBeInvalid(loginPage.getPasswordInput());
    }

    @Test
    void shouldCloseErrorPopup(){
        loginPage
                .fillCredential(TestData.INVALID_EMAIL, TestData.INVALID_PASSWORD)
                .clickSignIn()
                .closeErrorPopup();

    }

    @Test
    void RegistrationPageShouldOpen(){
        loginPage
                .clickRegistration()
                .shouldBeOpened()
        ;
    }













}
