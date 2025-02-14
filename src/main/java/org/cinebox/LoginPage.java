package org.cinebox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginPage {
    private Scene scene;

    public LoginPage(ApplicationManager appManager) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label loginLabel = new Label("Login");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Gebruikersnaam");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Wachtwoord");

        Label errorLabel = new Label();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();


        });

        Button backButton = new Button("Terug naar Home");
        backButton.setOnAction(e -> appManager.showHomePage());

        layout.getChildren().addAll(loginLabel, usernameField, passwordField, loginButton, errorLabel, backButton);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout, 1280, 720);
    }

    public Scene getScene() {
        return scene;
    }
}
