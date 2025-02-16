package org.cinebox;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class DetailPage {
    private Scene scene;

    public DetailPage(ApplicationManager applicationManager, Film film) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label titleLabel = new Label("Title: " + film.getTitle());
        Label descriptionLabel = new Label("Description: " + film.getDescription());
        Label directorLabel = new Label("Director: " + film.getDirector());
        Label reviewLabel = new Label("Review: " + film.getReview());
        Label genreLabel = new Label("Genre: " + film.getGenre());
        Label ratingLabel = new Label("Rating: " + film.getRating());

        ImageView posterImageView = new ImageView(film.getPoster());
        posterImageView.setFitWidth(200);
        posterImageView.setFitHeight(300);
        posterImageView.setPreserveRatio(true);

        Button backButton = new Button("Back to Home");
        backButton.setOnAction(e -> applicationManager.showHomePage());

        layout.getChildren().addAll(titleLabel, descriptionLabel, directorLabel, reviewLabel, genreLabel, ratingLabel, posterImageView, backButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        scene = new Scene(layout, 1270, 720);
    }

    public Scene getScene() {
        return scene;
    }
}