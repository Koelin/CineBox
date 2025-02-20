package org.cinebox;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

public class EditFilmPage {
    private Scene scene;
    private ImageView posterImageView;
    private File selectedImageFile;

    public EditFilmPage(ApplicationManager applicationManager, Film film) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));


        Label editFilmLabel = new Label("Film Bewerken");
        Button backButton = new Button("Terug naar Home");
        backButton.setOnAction(e -> applicationManager.showHomePage());

        Label titleLabel = new Label("title");
        TextField titleField = new TextField(film.getTitle());

        Label descriptionLabel = new Label("description");
        TextField descriptionField = new TextField(film.getDescription());

        Label genreLabel = new Label("genre");
        ChoiceBox<String> genreField = new ChoiceBox<>();
        genreField.getItems().addAll("Action", "Adventure", "Animation", "Comedy", "Crime", "Drama", "Fantasy",
                "Historical", "Horror", "Mystery", "Romance", "Science Fiction", "Thriller", "Western");
        genreField.setValue(film.getGenre());

        Label directorLabel = new Label("director");
        TextField directorField = new TextField(film.getDirector());

        Label reviewLabel = new Label("review");
        TextArea reviewTextArea = new TextArea(film.getReview());

        Label ratingLabel = new Label("rating");
        ChoiceBox<String> ratingField = new ChoiceBox<>();
        ratingField.getItems().addAll("1", "2", "3", "4", "5");
        ratingField.setValue(String.valueOf(film.getRating()));

        Label posterLabel = new Label("poster");
        posterImageView = new ImageView(film.getPoster());
        posterImageView.setFitWidth(200);
        posterImageView.setFitHeight(300);
        posterImageView.setPreserveRatio(true);

        Button selectImageButton = new Button("Selecteer Afbeelding");
        selectImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            selectedImageFile = fileChooser.showOpenDialog(applicationManager.getStage());
            if (selectedImageFile != null) {
                Image image = new Image(selectedImageFile.toURI().toString());
                posterImageView.setImage(image);
            }
        });

        Button saveFilmButton = new Button("Film Opslaan");
        saveFilmButton.setOnAction(e -> {
            film.setTitle(titleField.getText());
            film.setDescription(descriptionField.getText());
            film.setGenre(genreField.getValue());
            film.setDirector(directorField.getText());
            film.setReview(reviewTextArea.getText());
            film.setRating(Integer.parseInt(ratingField.getValue()));
            if (selectedImageFile != null) {
                film.setPoster(new Image(selectedImageFile.toURI().toString()));
            }

            applicationManager.showHomePage();

            FilmRepository.editFilm(film);
        });

        layout.getChildren().addAll(editFilmLabel, backButton, titleLabel, titleField, descriptionLabel, descriptionField,
                genreLabel, genreField, directorLabel, directorField, reviewLabel, reviewTextArea, ratingLabel,
                ratingField, posterLabel, posterImageView, selectImageButton, saveFilmButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        scene = new Scene(layout, 1270, 720);
        scene.getStylesheets().add(getClass().getResource("/Styles.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }
}