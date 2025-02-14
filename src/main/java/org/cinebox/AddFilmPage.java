package org.cinebox;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AddFilmPage {
    private Scene scene;

    public AddFilmPage(ApplicationManager applicationManager) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label addFilmLabel = new Label("Film Toevoegen");
        Button backButton = new Button("Terug naar Home");
        backButton.setOnAction(e -> applicationManager.showHomePage());

        TextField titleField = new TextField();
        titleField.setPromptText("Titel");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Beschrijving");

        TextField genreField = new TextField();
        genreField.setPromptText("Genre");

        TextField directorField = new TextField();
        directorField.setPromptText("Regisseur");

        TextArea reviewTextArea = new TextArea();
        reviewTextArea.setPromptText("Review");

        TextField ratingField = new TextField();
        ratingField.setPromptText("Rating");

        TextField posterField = new TextField();
        posterField.setPromptText("Poster");

        Button addFilmButton = new Button("Film Toevoegen");
        addFilmButton.setOnAction(e -> {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String rating = ratingField.getText();
            String genre = genreField.getText();
            String director = directorField.getText();
            String review = reviewTextArea.getText();
            String poster = posterField.getText();

            Film film = new Film(title, description, review, director, genre, poster);
            filmRepository.addFilm(film);

            applicationManager.showHomePage();
        });

        layout.getChildren().addAll(addFilmLabel, backButton, titleField, descriptionField, genreField, directorField, reviewTextArea, ratingField, posterField, addFilmButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);






        scene = new Scene(layout, 1270, 720);
    }

    public Scene getScene() {
        return scene;
    }
}
