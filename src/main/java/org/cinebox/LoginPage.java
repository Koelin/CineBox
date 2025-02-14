package org.cinebox;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginPage {

    private Scene scene;

    public LoginPage(ApplicationManager appManager) {
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        layout.setVgap(10);
        layout.setHgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            User user = UserRepository.getUser(username);
            if (user != null && user.getPassword().equals(password)) {
                appManager.setLoggedInUser(user);
                appManager.showHomePage();
            } else {
                Label errorLabel = new Label("Invalid username or password");
                layout.add(errorLabel, 1, 3);
            }
        });

        layout.add(usernameLabel, 0, 0);
        layout.add(usernameField, 1, 0);
        layout.add(passwordLabel, 0, 1);
        layout.add(passwordField, 1, 1);
        layout.add(loginButton, 1, 2);


        scene = new Scene(layout, 400, 300);
    }

    public Scene getScene() {
        return scene;
    }
}