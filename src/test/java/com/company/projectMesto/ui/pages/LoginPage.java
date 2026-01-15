package com.company.projectMesto.ui.pages;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginPage {
    private final SelenideElement emailInput = $("#email");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement signInButton = $(".auth-form__button");

    private final SelenideElement registButton = $("a.header__auth-link");

    private final SelenideElement errorPopup = $(".popup.popup_is-opened");
    private final SelenideElement errorText = errorPopup.$(".popup__status-message");
    private final SelenideElement closeErrorButton = errorPopup.$("button.popup__close");

    private final SelenideElement footerText = $(".footer__copyright");

    public LoginPage openPage(){
        open("/signin");
        signInButton.shouldBe(visible);
        return this;
    }

    public LoginPage shouldBeOpened(){
        signInButton.shouldBe(visible);
        return this;
    }

    public LoginPage clickSignIn(){
        signInButton.shouldBe(enabled).click();
        return this;
    }

    public LoginPage fillCredential(String email, String password){
        emailInput.shouldBe(visible).setValue(email);
        passwordInput.shouldBe(visible).setValue(password);
        return this;
    }

    public HomePage login(String email, String password) {
        fillCredential(email, password);
        clickSignIn();
        return new HomePage();
    }

    public LoginPage closeErrorPopup(){
        errorPopup.shouldBe(visible);
        closeErrorButton.shouldBe(enabled).click();
        errorPopup.shouldNot(exist);
        return this;
    }


    public LoginPage shouldShowError(String line1, String line2) {
        errorPopup.shouldBe(visible);
        errorText.shouldHave(text(line1))
                .shouldHave(text(line2));
        return this;
    }

    public RegistretionPage clickRegistration(){
        registButton.shouldBe(interactable).click();
        return new RegistretionPage().shouldBeOpened();
    }


    public SelenideElement getEmailInput() {
        return emailInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }
}
