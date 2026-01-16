package com.company.projectMesto.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.company.projectMesto.ui.components.PostCardComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class HomePage {

    // main Info
    private final SelenideElement headerUser = $(".header__user");
    private final SelenideElement profileName = $("h1.profile__title");
    private final SelenideElement profileDescription = $("p.profile__description");
    private final SelenideElement footerText = $(".footer__copyright");

    // main buttons
    private final SelenideElement logoutButton = $(".header__logout");
    private final SelenideElement addPostButton = $(".profile__add-button");
    private final SelenideElement editProfileButton = $(".profile__edit-button");
    private final SelenideElement editProfileImageButton = $(".profile__image");

    // registration Popup
    private final SelenideElement registrationPopup = $(".popup.popup_is-opened");
    private final SelenideElement registrationStatusMessage = registrationPopup.$(".popup__status-message");
    private final SelenideElement closeRegistrationPopup = registrationPopup.$(".popup__close");

    // posts
    private final SelenideElement listOfPosts = $("ul.places__list");
    private final ElementsCollection cards = listOfPosts.$$("li.places__item.card");


    private SelenideElement findPostByTitle(String title){
        listOfPosts.shouldBe(visible);
        cards.shouldHave(sizeGreaterThan(0));

        for (SelenideElement card : cards) {
            SelenideElement titleEl = card.$("h2.card__title");
            if (titleEl.exists() && titleEl.getText().trim().equals(title)) {
                return card;
            }
        }
        throw new AssertionError("Post card not found by title: " + title);
    }




    public HomePage shouldBeOpened(){
        logoutButton.shouldBe(visible);
        return this;
    }

    @Step("Logout")
    public LoginPage clickLogout(){
        logoutButton.shouldBe(interactable).click();
        return new LoginPage();
    }

    @Step("Get post card by title: {title}")
    public PostCardComponent cardByTitle(String title){
        return new PostCardComponent(findPostByTitle(title));
    }



    // REGISTRATION METHODS --------------------------------------------

    @Step("Expect registration message : {message} ")
    public HomePage shouldShowRegistrationMessage(String message){
        registrationPopup.shouldBe(visible);
        registrationStatusMessage.shouldHave(text(message));
        return this;
    }

    @Step("Close registration status popup")
    public HomePage closeRegistrationStatusMessage(){
        closeRegistrationPopup.shouldBe(interactable).click();
        registrationPopup.shouldNot(exist);
        return this;
    }






}
