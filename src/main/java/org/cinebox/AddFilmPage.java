package org.cinebox;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

import java.io.File;

public class AddFilmPage {
    private Scene scene;
    private ImageView posterImageView;

    public AddFilmPage(ApplicationManager applicationManager) {
        VBox layout = new VBox(10);
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

        posterImageView = new ImageView();
        posterImageView.setFitWidth(200);
        posterImageView.setFitHeight(300);
        posterImageView.setPreserveRatio(true);
        posterImageView.setOnDragOver(this::handleDragOver);
        posterImageView.setOnDragDropped(this::handleDragDropped);

        Button addFilmButton = new Button("Film Toevoegen");
        addFilmButton.setOnAction(e -> {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String rating = ratingField.getText();
            String genre = genreField.getText();
            String director = directorField.getText();
            String review = reviewTextArea.getText();
            Image poster = posterImageView.getImage();

            User loggedInUser = applicationManager.getLoggedInUser();
            int userId = loggedInUser.getId();
            int genreId = 0;

            Film film = new Film(title, description, review, director, genreId, userId, poster);

            applicationManager.showHomePage();
        });

        layout.getChildren().addAll(addFilmLabel, backButton, titleField, descriptionField, genreField, directorField, reviewTextArea, ratingField, posterImageView, addFilmButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        scene = new Scene(layout, 1270, 720);
    }

    public Scene getScene() {
        return scene;
    }

    private void handleDragOver(DragEvent event) {
        if (event.getGestureSource() != posterImageView && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    private void handleDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            File file = db.getFiles().get(0);
            Image image = new Image(file.toURI().toString());
            posterImageView.setImage(image);
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    }
}