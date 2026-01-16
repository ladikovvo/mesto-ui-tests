package com.company.projectMesto.ui.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.CssClass;
import com.company.projectMesto.ui.pages.HomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class PostCardComponent {
    private SelenideElement post;

    public PostCardComponent(SelenideElement post) {
        this.post = post;
        this.title = this.post.$("h2.card__title");
        this.likesCount = this.post.$("p.card__like-count");
        this.likeButton = this.post.$("button.card__like-button");
    }



    private final SelenideElement title;
    private final SelenideElement likesCount;
    private final SelenideElement likeButton;


    private final String likeButtonTrue = "card__like-button_is-active";
    private final String likeButtonFalse = "false";


    public String title(){
        return title
                .shouldBe(visible)
                .getText()
                .trim();
    }


    @Step("Read likes counter")
    public int likesCount(){
        String text = likesCount
                .shouldBe(visible)
                .getText()
                .trim();

        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }



    @Step("Wait likes counter changed from {old}")
    public PostCardComponent waitLikesChangedFrom(int old) {
        likesCount.shouldNotHave(exactText(String.valueOf(old)));
        return this;
    }

    @Step("Ensure post is not liked")
    public PostCardComponent ensureNotLiked(){
        if(likeButton.shouldBe(visible).has(cssClass(likeButtonTrue))){
            unlikePost();
        }
        return this;
    }

    @Step("Ensure post is liked")
    public PostCardComponent ensureLiked(){
        if(!likeButton.shouldBe(visible).has(cssClass(likeButtonTrue))){
            likePost();
        }
        return this;
    }



    @Step("Like post")
    public PostCardComponent likePost(){
        likeButton
                .shouldBe(interactable)
                .shouldNotHave(cssClass(likeButtonTrue))
                .click();

        likeButton.shouldHave(cssClass(likeButtonTrue));
        return this;

    }

    @Step("Unlike post")
    public PostCardComponent unlikePost() {


        likeButton.shouldBe(interactable)
                .shouldHave(cssClass("card__like-button_is-active"))
                .click();

        likeButton.shouldNotHave(cssClass(likeButtonTrue));
        return this;
    }




}
