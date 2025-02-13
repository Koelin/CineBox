package org.cinebox;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomePage {

    private Scene Scene;

    public HomePage(ApplicationManager appManager) {

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label welcomeLabel = new Label("Welkom bij FilmApp!");

        Button loginButton = new Button("Login");
//        loginButton.setOnAction(e -> appManager.showLoginPage());

        Button addFilmButton = new Button("Film Toevoegen");
//        addFilmButton.setOnAction(e -> appManager.showFilmAddPage());

        layout.getChildren().addAll(welcomeLabel, loginButton, addFilmButton);

        Scene = new Scene(layout, 400, 300);

    }

    public Scene getScene() {
        return Scene;
    }
}
