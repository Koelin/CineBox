package org.cinebox;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

        TextField titleField = new TextField(film.getTitle());
        TextField descriptionField = new TextField(film.getDescription());
        TextField genreField = new TextField(film.getGenre());
        TextField directorField = new TextField(film.getDirector());
        TextArea reviewTextArea = new TextArea(film.getReview());
        TextField ratingField = new TextField(String.valueOf(film.getRating()));

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
            film.setGenre(genreField.getText());
            film.setDirector(directorField.getText());
            film.setReview(reviewTextArea.getText());
            film.setRating(Integer.parseInt(ratingField.getText()));
            if (selectedImageFile != null) {
                film.setPoster(new Image(selectedImageFile.toURI().toString()));
            }

            applicationManager.showHomePage();

            FilmRepository.editFilm(film);
        });

        layout.getChildren().addAll(editFilmLabel, backButton, titleField, descriptionField, genreField, directorField, reviewTextArea, ratingField, selectImageButton, posterImageView, saveFilmButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        scene = new Scene(layout, 1270, 720);
        scene.getStylesheets().add(getClass().getResource("/Styles.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }
}