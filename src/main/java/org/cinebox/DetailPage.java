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
        layout.getStyleClass().add("root");

        Label titleLabel = new Label("Title: " + film.getTitle());
        titleLabel.getStyleClass().add("label-detail-title");

        Label descriptionLabel = new Label("Description: " + film.getDescription());
        descriptionLabel.getStyleClass().add("label-detail-description");

        Label directorLabel = new Label("Director: " + film.getDirector());
        directorLabel.getStyleClass().add("label-detail-director");

        Label reviewLabel = new Label("Review: " + film.getReview());
        reviewLabel.getStyleClass().add("label-detail-description");

        Label genreLabel = new Label("Genre: " + film.getGenre());
        genreLabel.getStyleClass().add("label-detail-description");

        Label ratingLabel = new Label("Rating: " + film.getRating());
        ratingLabel.getStyleClass().add("label-detail-description");

        ImageView posterImageView = new ImageView(film.getPoster());
        posterImageView.setFitWidth(200);
        posterImageView.setFitHeight(300);
        posterImageView.setPreserveRatio(true);
        posterImageView.getStyleClass().add("poster-image-detail");

        Button backButton = new Button("Back to Home");
        backButton.getStyleClass().add("button-back");
        backButton.setOnAction(e -> applicationManager.showHomePage());

        layout.getChildren().addAll(titleLabel, descriptionLabel, directorLabel, reviewLabel, genreLabel, ratingLabel, posterImageView, backButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        scene = new Scene(layout, 1270, 720);
        scene.getStylesheets().add(getClass().getResource("/Styles.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }
}