package org.cinebox;

import javafx.stage.Stage;

public class ApplicationManager {
    private Stage stage;
    private User loggedInUser;

    public ApplicationManager(Stage stage) {
        this.stage = stage;
    }

    public void showRegisterUserPage() {
        RegisterUserPage registerUserPage = new RegisterUserPage(this);
        stage.setScene(registerUserPage.getScene());
        stage.show();
    }

    public void showHomePage() {
        HomePage homePage = new HomePage(this);
        stage.setScene(homePage.getScene());
        stage.show();
    }

    public void showAddFilmPage() {
        AddFilmPage addFilmPage = new AddFilmPage(this);
        stage.setScene(addFilmPage.getScene());
        stage.show();
    }

    public void showLoginPage() {
        LoginPage loginPage = new LoginPage(this);
        stage.setScene(loginPage.getScene());
        stage.show();
    }

    public void showDetailPage(Film film) {
        DetailPage detailPage = new DetailPage(this, film);
        stage.setScene(detailPage.getScene());
        stage.show();
    }

    public void showEditFilmPage(Film film) {
        EditFilmPage editFilmPage = new EditFilmPage(this, film);
        stage.setScene(editFilmPage.getScene());
        stage.show();
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public Stage getStage() {
        return stage;
    }
}