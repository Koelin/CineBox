package org.cinebox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LoginPage {

    private Scene scene;

    public LoginPage(ApplicationManager appManager) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("root");

        Label loginLabel = new Label("Login");
        loginLabel.getStyleClass().add("label-login-title");

        Label usernameLabel = new Label("Username:");
        usernameLabel.getStyleClass().add("label-username");

        TextField usernameField = new TextField();

        usernameField.getStyleClass().add("textfield-username");

        Label passwordLabel = new Label("Password:");
        passwordLabel.getStyleClass().add("label-password");

        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("textfield-password");


        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("button-submit");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            User user = UserRepository.getUser(username);
            if (user != null && user.getPassword().equals(password)) {
                appManager.setLoggedInUser(user);
                appManager.showHomePage();
            } else {
                Label errorLabel = new Label("Invalid username or password");
                layout.getChildren().add(errorLabel);
            }
        });

layout.getChildren().addAll(loginLabel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton);


        scene = new Scene(layout, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("/Styles.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }
}