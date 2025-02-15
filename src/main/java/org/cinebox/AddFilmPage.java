package org.cinebox;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

public class AddFilmPage {
    private Scene scene;
    private ImageView posterImageView;
    private File selectedImageFile;

    public AddFilmPage(ApplicationManager applicationManager) {
    VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

    Label addFilmLabel = new Label("Film Toevoegen");
    Button backButton = new Button("Terug naar Home");
        backButton.setOnAction(e -> applicationManager.showHomePage());

    TextField titleField = new TextField();
        titleField.setPromptText("Titel");

    TextField descriptionField = new TextField();
    TextField genreField = new TextField();
        genreField.setPromptText("Genre");

    TextField directorField = new TextField();
        directorField.setPromptText("Regisseur");

    TextArea reviewTextArea = new TextArea();
        reviewTextArea.setPromptText("Review");

//    TextField ratingField = new TextField();
//        ratingField.setPromptText("Rating");

        // a choice box for the rating
        ChoiceBox<String> ratingField = new ChoiceBox<>();
        ratingField.getItems().addAll("1", "2", "3", "4", "5");
        ratingField.setValue("1");


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
        Image poster = posterImageView.getImage();

        User loggedInUser = applicationManager.getLoggedInUser();
        int userId = loggedInUser.getId();
        int genreId = 0;

        //Film film = new Film(title, description, review, director, genreId, userId, poster);
        Film film = new Film(title, description, review, director, genreId, userId, poster, genre, rating);
        loggedInUser.addFilm(film);

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