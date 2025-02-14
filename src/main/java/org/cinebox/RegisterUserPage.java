package org.cinebox;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RegisterUserPage {

    private Scene scene;

    public RegisterUserPage(ApplicationManager appManager) {

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Gebruikersnaam");

        TextField passwordField = new TextField();
        passwordField.setPromptText("Wachtwoord");

        Button registerButton = new Button("Registreren");

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            User user = new User(username, password);
            UserRepository.addUser(user);

            appManager.setLoggedInUser(user);
            appManager.showHomePage();
        });



        layout.getChildren().addAll(usernameField, passwordField, registerButton);
        scene = new Scene(layout, 1280, 720);

    }

    public Scene getScene() {
        return scene;
    }







}
