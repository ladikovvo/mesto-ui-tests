package com.company.mesto.ui.tests;

import com.company.mesto.testdata.CommonTestData;
import com.company.mesto.ui.components.PostCardComponent;
import com.company.mesto.ui.config.UiConfig;
import com.company.mesto.ui.pages.HomePage;
import com.company.mesto.ui.pages.LoginPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Tag("ui")
@Tag("authorized")
@DisplayName("Home page tests")
public class AuthorizedTests extends UiConfig {

    private HomePage home;

    @BeforeEach
    void login(){
        home = new LoginPage()
                .openPage()
                .login(CommonTestData.EMAIL, CommonTestData.PASSWORD)
                .shouldBeOpened();
    }


    @Tag("smoke")
    @Test
    @DisplayName("Logout")
    @Timeout(20)
    void logout(){
        home.clickLogout()
                .shouldBeOpened();
    }

    @Test
    @DisplayName("Header should contain user email: {expectedEmail} ")
    void headerShouldContainUserEmail(){
        String expectedEmail = CommonTestData.EMAIL;
        String headerEmail = home.getHeaderUserEmail();
        assertEquals(expectedEmail , headerEmail, "Header user email is not correct");
    }


    @Nested
    @DisplayName("Profile edit Popup")
    class Profile{

        @Test
        @DisplayName("Edit profile name")
        void editProfileName(){

            String newName = CommonTestData.randomString();
            String oldName = home.getProfileName();

            home.openEditProfilePopup()
                    .editProfileName(newName)
                    .saveProfile();

            home.waitProfileNameChanged(oldName);

            assertEquals(newName , home.getProfileName());
        }

        @Test
        @DisplayName("Edit profile activity")
        void editProfileActivity(){
            String newActivity = CommonTestData.randomString();
            String oldActivity = home.getProfileActivity();

            home.openEditProfilePopup()
                    .editProfileActivity(newActivity)
                    .saveProfile();
            home.waitProfileActivityChanged(oldActivity);

            assertEquals(newActivity, home.getProfileActivity());
        }

        @Test
        @DisplayName("Close popup - edit profile")
        void closeProfileEditPopup(){
            home.openEditProfilePopup()
                    .closeEditProfilePopup();
        }

    }

    @Nested
    @DisplayName("Posts")
    class Posts {

        @Test
        @DisplayName("Should throw if post not found by title")
        void shouldThrowIfPostNotFound() {
            AssertionError e = assertThrows(
                    AssertionError.class,
                    () -> home.cardByTitle("NO_SUCH_TITLE_123")
            );

            assertTrue(
                    e.getMessage().contains("Post card not found"),
                    "Error message should explain why the post was not found"
            );
        }

        @Tag("likes")
        @Test
        @DisplayName("Should increase like counter")
//        @EnabledIfEnvironmentVariable(named = "RUN_UI", matches = "true")
        void shouldIncreaseCounter(){
            PostCardComponent post = home.cardByTitle("Москва140101");
            post.ensureNotLiked();
            int old = post.likesCount();
            post.likePost();
            post.waitLikesChangedFrom(old);
            int actual = post.likesCount();
            assertAll("Likes counter should change after like",
                    () -> assertNotEquals(old , actual, "Counter didn't change"),
                    () -> assertTrue(actual >= 0, "Counter should not be negative")
            );
        }
    }












}
