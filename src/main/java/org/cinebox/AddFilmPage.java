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
import java.util.Objects;

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


        ChoiceBox<String> genreField = new ChoiceBox<>();
        genreField.getItems().addAll("Action", "Adventure", "Animation", "Comedy", "Crime", "Drama", "Fantasy",
                                           "Historical", "Horror", "Mystery", "Romance", "Science Fiction", "Thriller", "Western");
        genreField.setValue("Action");

        TextField directorField = new TextField();
        directorField.setPromptText("Regisseur");

        TextArea reviewTextArea = new TextArea();
        reviewTextArea.setPromptText("Review");

        ChoiceBox<String> ratingField = new ChoiceBox<>();
        ratingField.getItems().addAll("1", "2", "3", "4", "5");
        ratingField.setValue("1");

        posterImageView = new ImageView();
        posterImageView.setFitWidth(200);
        posterImageView.setFitHeight(300);
        posterImageView.setPreserveRatio(true);

        backButton.setOnAction(e -> applicationManager.showHomePage());

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
            Integer rating = Integer.parseInt(ratingField.getValue());
            String genre = genreField.getValue();
            String director = directorField.getText();
            String review = reviewTextArea.getText();
            byte[] posterBytes = null;

            // Convert the selected image file to a byte array
            if (selectedImageFile != null) {
                try (FileInputStream fis = new FileInputStream(selectedImageFile);
                     ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[1024]; // 1 KB
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        bos.write(buffer, 0, bytesRead);
                    }
                    posterBytes = bos.toByteArray();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            if (posterBytes == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Image Error");
                alert.setContentText("Failed to load the image. Please select a valid image file.");
                alert.showAndWait();
                return;
            }

            User loggedInUser = applicationManager.getLoggedInUser();
            if (loggedInUser == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("User Error");
                alert.setContentText("No user is logged in. Please log in and try again.");
                alert.showAndWait();
                return;
            }

            int userId = loggedInUser.getId();
            int genreId = GenreRepository.getGenreIdByName(genre);

            if (genreId == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Genre");
                alert.setContentText("The specified genre does not exist.");
                alert.showAndWait();
                return;
            }

            Film film = new Film(title, description, director, genreId, userId, posterBytes, genre, review, rating);
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