package com.company.projectMesto.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistretionPage {
    private final SelenideElement registrateButton = $$("button.auth-form__button").findBy(exactText("Зарегистрироваться"));
    private final SelenideElement emailInput = $("#email");
    private final SelenideElement passwordInput = $("#password");

    private final SelenideElement headerSignInButton = $(".header__auth-link");
    private final SelenideElement signInButton = $(".auth-form__link");


    public RegistretionPage shouldBeOpened(){
        registrateButton.shouldBe(visible);
        return this;
    }

    public HomePage registerNewUser(String email, String password){
        emailInput.shouldBe(interactable).setValue(email);
        passwordInput.shouldBe(interactable).setValue(password);
        registrateButton.shouldBe(interactable).click();
        return new HomePage().shouldBeOpened();
    }





}
