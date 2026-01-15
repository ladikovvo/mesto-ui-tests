package com.company.projectMesto.ui.tests;

import com.company.projectMesto.ui.components.PostCardComponent;
import com.company.projectMesto.ui.config.TestBase;
import com.company.projectMesto.ui.config.TestData;
import com.company.projectMesto.ui.pages.HomePage;
import com.company.projectMesto.ui.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizedTests extends TestBase {

    private HomePage home;

    @BeforeEach
    void login(){
        home = new LoginPage()
                .openPage()
                .login(TestData.EMAIL, TestData.PASSWORD)
                .shouldBeOpened();
    }

    @Test
    void logout(){
        home.clickLogout()
                .shouldBeOpened();
    }



    @Test
    void shouldIncreaseCounter(){
        PostCardComponent post = home.cardByTitle("Москва140101");
        post.ensureNotLiked();
        int old = post.likesCount();
        post.likePost();
        post.waitLikesChangedFrom(old);
        int actual = post.likesCount();
        assertNotEquals(old , actual, "Counter didn't change");

    }






}
