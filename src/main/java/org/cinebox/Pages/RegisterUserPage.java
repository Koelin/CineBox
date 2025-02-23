package org.cinebox.Pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.cinebox.ApplicationManager;
import org.cinebox.Classes.User;
import org.cinebox.Repositories.UserRepository;

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
        usernameField.setPromptText("Username");
        usernameField.getStyleClass().add("textfield-username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().add("textfield-password");

        Label errorLabel = new Label();
        errorLabel.getStyleClass().add("label-error");

        Button registerButton = new Button("Registreren");
        registerButton.getStyleClass().add("button-submit");

        Button inlogButton = new Button("Log in");
        inlogButton.getStyleClass().add("button-submit");

        inlogButton.setOnAction(e -> appManager.showLoginPage());

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (UserRepository.usernameExists(username)) {
                errorLabel.setText("User already exists");

            } else {
                User user = new User(1, username, password);
                UserRepository.addUser(user);
                appManager.setLoggedInUser(user);
                appManager.showHomePage();
            }
        });

        layout.getChildren().addAll(registerLabel, usernameField, passwordField, registerButton, inlogButton, errorLabel);

        scene = new Scene(layout, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("/Styles.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }
}