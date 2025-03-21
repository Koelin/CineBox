package org.cinebox.Pages;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.cinebox.*;
import org.cinebox.Classes.Film;
import org.cinebox.Classes.User;
import org.cinebox.Repositories.FilmRepository;
import org.cinebox.Repositories.GenreRepository;

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
        layout.getStyleClass().add("root");

        Label addFilmLabel = new Label("Add Film");
        addFilmLabel.getStyleClass().add("label-add-film-title");

        Button backButton = new Button("Home");
        backButton.getStyleClass().add("button-submit");

        Label titleLabel = new Label("Title");
        titleLabel.getStyleClass().add("label-add-film-title");

        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        titleField.getStyleClass().add("textfield-add-film-title");

        Label descriptionLabel = new Label("Description");
        descriptionLabel.getStyleClass().add("label-add-film-description");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("description");
        descriptionField.getStyleClass().add("textfield-add-film-description");

        Label genreLabel = new Label("Genre");
        genreLabel.getStyleClass().add("label-add-film-director");

        ChoiceBox<String> genreField = new ChoiceBox<>();
        genreField.getItems().addAll("Action", "Adventure", "Animation", "Comedy", "Crime", "Drama", "Fantasy",
                                           "Historical", "Horror", "Mystery", "Romance", "Science Fiction", "Thriller", "Western");
        genreField.setValue("Action");

        Label directorLabel = new Label("Director");
        directorLabel.getStyleClass().add("label-add-film-director");

        TextField directorField = new TextField();
        directorField.setPromptText("Regisseur");
        directorField.getStyleClass().add("textfield-add-film-director");

        Label reviewLabel = new Label("Review");
        reviewLabel.getStyleClass().add("label-add-film-director");

        TextArea reviewTextArea = new TextArea();
        reviewTextArea.setPromptText("Review");
        reviewTextArea.getStyleClass().add("text-area-add-film-description");

        Label ratingLabel = new Label("Rating");
        ratingLabel.getStyleClass().add("label-add-film-director");

        ChoiceBox<String> ratingField = new ChoiceBox<>();
        ratingField.getItems().addAll("1", "2", "3", "4", "5");
        ratingField.setValue("1");

        posterImageView = new ImageView();
        posterImageView.setFitWidth(200);
        posterImageView.setFitHeight(300);
        posterImageView.setPreserveRatio(true);

        backButton.setOnAction(e -> applicationManager.showHomePage());

        Button selectImageButton = new Button("Selecteer Afbeelding");
        selectImageButton.getStyleClass().add("button-submit");
        selectImageButton.setOnAction(e -> {
            // Open a file chooser dialog to select an image file
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            selectedImageFile = fileChooser.showOpenDialog(applicationManager.getStage());
            if (selectedImageFile != null) {
                Image image = new Image(selectedImageFile.toURI().toString());
                posterImageView.setImage(image);
            }
        });

        Button addFilmButton = new Button("Add film");
        addFilmButton.getStyleClass().add("button-submit");
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

            // Display an error message if the image file could not be loaded
            if (posterBytes == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Image Error");
                alert.setContentText("Failed to load the image. Please select a valid image file.");
                alert.showAndWait();
                return;
            }

            // Get the ID of the logged in user
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

            // Display an error message if the genre does not exist (should never happen)
            if (genreId == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Genre");
                alert.setContentText("The specified genre does not exist.");
                alert.showAndWait();
                return;
            }

            // Create a new film object and add it to the database
            Film film = new Film(title, description, director, genreId, userId, posterBytes, genre, review, rating);
            FilmRepository.addFilm(film);

            applicationManager.showHomePage();
        });

       layout.getChildren().addAll(addFilmLabel, titleLabel, titleField, descriptionLabel, descriptionField, genreLabel, genreField, directorLabel, directorField, reviewLabel, reviewTextArea, ratingLabel, ratingField, selectImageButton, posterImageView, addFilmButton, backButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scene = new Scene(scrollPane, 1270, 720);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles.css")).toExternalForm());

    }

    public Scene getScene() {
        return scene;
    }
}