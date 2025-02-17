package org.cinebox;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AddFilmPage {
    private Scene scene;
    private ImageView posterImageView;
    private File selectedImageFile;

    public AddFilmPage(ApplicationManager applicationManager) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label addFilmLabel = new Label("Film Toevoegen");
        Button backButton = new Button("Terug naar Home");
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

        ChoiceBox<String> ratingField = new ChoiceBox<>();
        ratingField.getItems().addAll("1", "2", "3", "4", "5");

        posterImageView = new ImageView();
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

        Button addFilmButton = new Button("Film Toevoegen");
        addFilmButton.setOnAction(e -> {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String rating = ratingField.getValue();
            String genre = genreField.getText();
            String director = directorField.getText();
            String review = reviewTextArea.getText();
            byte[] posterBytes = null;

            if (selectedImageFile != null) {
                try (FileInputStream fis = new FileInputStream(selectedImageFile);
                     ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        bos.write(buffer, 0, bytesRead);
                    }
                    posterBytes = bos.toByteArray();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            User loggedInUser = applicationManager.getLoggedInUser();
            int userId = loggedInUser.getId();
            int genreId = 0; // You need to fetch the genreId from the database based on the genre name

            Film film = new Film(title, description, review, director, genreId, userId, posterBytes, genre, rating);
            FilmRepository.addFilm(film);

            applicationManager.showHomePage();
        });

        layout.getChildren().addAll(addFilmLabel, backButton, titleField, descriptionField, genreField, directorField, reviewTextArea, ratingField, selectImageButton, posterImageView, addFilmButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        scene = new Scene(layout, 1270, 720);
    }

    public Scene getScene() {
        return scene;
    }
}