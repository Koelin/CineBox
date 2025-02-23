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
        layout.getStyleClass().add("root");


        Label editFilmLabel = new Label("Film Bewerken");
        editFilmLabel.getStyleClass().add("label-title");
        Button backButton = new Button("Terug naar Home");
        backButton.getStyleClass().add("button-back");
        backButton.setOnAction(e -> applicationManager.showHomePage());

        Label titleLabel = new Label("title");
        titleLabel.getStyleClass().add("label-detail-title");
        TextField titleField = new TextField(film.getTitle());
        titleField.getStyleClass().add("textfield-edit-film-title");

        Label descriptionLabel = new Label("description");
        descriptionLabel.getStyleClass().add("label-edit-film-description");

        TextField descriptionField = new TextField(film.getDescription());
        descriptionField.getStyleClass().add("textfield-edit-film-description");

        Label genreLabel = new Label("genre");

        ChoiceBox<String> genreField = new ChoiceBox<>();
        genreField.getItems().addAll("Action", "Adventure", "Animation", "Comedy", "Crime", "Drama", "Fantasy",
                "Historical", "Horror", "Mystery", "Romance", "Science Fiction", "Thriller", "Western");
        genreField.setValue(film.getGenre());

        Label directorLabel = new Label("director");
        directorLabel.getStyleClass().add("label-edit-film-director");
        TextField directorField = new TextField(film.getDirector());
        directorField.getStyleClass().add("textfield-edit-film-director");

        Label reviewLabel = new Label("review");
        reviewLabel.getStyleClass().add("label-edit-film-review");
        TextArea reviewTextArea = new TextArea(film.getReview());
        reviewTextArea.getStyleClass().add("text-area-edit-film-description");

        Label ratingLabel = new Label("rating");
        ratingLabel.getStyleClass().add("label-edit-film-rating");
        ChoiceBox<String> ratingField = new ChoiceBox<>();
        ratingField.getItems().addAll("1", "2", "3", "4", "5");
        ratingField.setValue(String.valueOf(film.getRating()));

        Label posterLabel = new Label("poster");
        posterImageView = new ImageView(film.getPoster());
        posterImageView.setFitWidth(200);
        posterImageView.setFitHeight(300);
        posterImageView.setPreserveRatio(true);

        Button selectImageButton = new Button("Selecteer Afbeelding");
        selectImageButton.getStyleClass().add("button-select-image");
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
        saveFilmButton.getStyleClass().add("button-edit-film-submit");
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


            FilmRepository.editFilm(film);
            applicationManager.showHomePage();


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