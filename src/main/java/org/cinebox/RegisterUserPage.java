package org.cinebox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RegisterUserPage {
    private Scene scene;

    public RegisterUserPage(ApplicationManager appManager) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("root");

        Label registerLabel = new Label("Registreren");
        registerLabel.getStyleClass().add("label-login-title");


        TextField usernameField = new TextField();
        usernameField.setPromptText("Gebruikersnaam");
        usernameField.getStyleClass().add("textfield-username");

        TextField passwordField = new TextField();
        passwordField.setPromptText("Wachtwoord");
        passwordField.getStyleClass().add("textfield-password");

        Button registerButton = new Button("Registreren");
        registerButton.getStyleClass().add("button-submit");

        Button inlogButton = new Button("Inloggen");
        inlogButton.getStyleClass().add("button-submit");

        inlogButton.setOnAction(e -> appManager.showLoginPage());

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            User user = new User(1, username, password);
            UserRepository.addUser(user);

            appManager.setLoggedInUser(user);
            appManager.showHomePage();
        });

        layout.getChildren().addAll(registerLabel,usernameField, passwordField, registerButton, inlogButton);

        // Create the scene and make it fullscreen
        scene = new Scene(layout,1280,720);

        scene.getStylesheets().add(getClass().getResource("/Styles.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }
}