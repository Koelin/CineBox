package org.cinebox;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class HomePage {
    private Scene scene;

    public HomePage(ApplicationManager applicationManager) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label homeLabel = new Label("Welkom bij Cinebox! " + applicationManager.getLoggedInUser().getUsername());
        layout.getChildren().add(homeLabel);

        Button registerButton = new Button("Registreren");
        registerButton.setOnAction(e -> applicationManager.showRegisterUserPage());

        Button loginButton = new Button("Inloggen");
        loginButton.setOnAction(e -> applicationManager.showLoginPage());

        Button addFilmButton = new Button("Film Toevoegen");
        addFilmButton.setOnAction(e -> applicationManager.showAddFilmPage());

        layout.getChildren().addAll(addFilmButton, registerButton, loginButton);

        User loggedInUser = applicationManager.getLoggedInUser();
        for (Film film : loggedInUser.getFilms()) {
            Label filmLabel = new Label(film.getTitle());
            Label directorLabel = new Label(film.getDirector());
            Label reviewLabel = new Label(film.getReview());
            Image poster = film.getPoster();
            ImageView posterImageView = new ImageView(poster);
            posterImageView.setFitWidth(200);
            posterImageView.setFitHeight(300);
            posterImageView.setPreserveRatio(true);

            layout.getChildren().addAll(posterImageView, filmLabel, directorLabel, reviewLabel);


        }

        scene = new Scene(layout, 1270, 720);
    }

    public Scene getScene() {
        return scene;
    }
}