package org.cinebox;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ApplicationManager {

    private Stage primaryStage;

    public ApplicationManager(Stage stage) {
        this.primaryStage = stage;
    }

    public void showHomePage() {
        HomePage homePage = new HomePage(this);
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
}
