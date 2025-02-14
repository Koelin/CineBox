package org.cinebox;

import javafx.stage.Stage;

public class ApplicationManager {

    private Stage primaryStage;
    private User loggedInUser;

    public ApplicationManager(Stage stage) {
        this.primaryStage = stage;
    }

    public void showHomePage() {
        HomePage homePage = new HomePage(this, loggedInUser);
        homePage.refreshFilmList();
        primaryStage.setScene(homePage.getScene());
        primaryStage.show();
    }

    public void showLoginPage() {
        LoginPage loginPage = new LoginPage(this);
        primaryStage.setScene(loginPage.getScene());
        primaryStage.show();
    }

    public void showFilmAddPage() {
        AddFilmPage filmAddPage = new AddFilmPage(this);
        primaryStage.setScene(filmAddPage.getScene());
        primaryStage.show();
    }

    public void showRegisterUserPage() {
        RegisterUserPage registerUserPage = new RegisterUserPage(this);
        primaryStage.setScene(registerUserPage.getScene());
        primaryStage.show();
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}