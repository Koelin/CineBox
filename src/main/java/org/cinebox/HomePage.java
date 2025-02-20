package org.cinebox;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class HomePage {
    private Scene scene;

    public HomePage(ApplicationManager applicationManager) {
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getStyleClass().add("root");

        Label homeLabel = new Label("Welcome" + applicationManager.getLoggedInUser().getUsername() + "to Cinebox" );
        homeLabel.getStyleClass().add("label-title");
        mainLayout.getChildren().add(homeLabel);

        Button registerButton = new Button("Registreren");
        registerButton.getStyleClass().add("button-register");
        registerButton.setOnAction(e -> applicationManager.showRegisterUserPage());

        Button loginButton = new Button("Inloggen");
        loginButton.getStyleClass().add("button-login");
        loginButton.setOnAction(e -> applicationManager.showLoginPage());

        Button addFilmButton = new Button("Film Toevoegen");
        addFilmButton.getStyleClass().add("button-add-film");
        addFilmButton.setOnAction(e -> applicationManager.showAddFilmPage());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addFilmButton, registerButton, loginButton);
        mainLayout.getChildren().add(buttonBox);

        User loggedInUser = applicationManager.getLoggedInUser();
        List<Film> films = FilmRepository.getFilmsByUser(loggedInUser.getId());

        HBox filmRow = new HBox(20);
        filmRow.setPadding(new Insets(10));
        filmRow.getStyleClass().add("film-row");

        for (Film film : films) {
            VBox filmBox = new VBox(10);
            filmBox.setPadding(new Insets(10));
            filmBox.getStyleClass().add("film-box");

            Image poster = film.getPoster();
            ImageView posterImageView = new ImageView();
            if (poster != null) {
                posterImageView.setImage(poster);
                posterImageView.setFitWidth(150);
                posterImageView.setFitHeight(150);
                posterImageView.setPreserveRatio(true);
                posterImageView.getStyleClass().add("poster-image");
            }

            Label filmTitleLabel = new Label(film.getTitle());
            filmTitleLabel.getStyleClass().add("label-film-title");

            Label filmDirectorLabel = new Label(film.getDirector());
            filmDirectorLabel.getStyleClass().add("label-film-director");

            Button detailButton = new Button("View Details");
            detailButton.getStyleClass().add("button-detail");
            detailButton.setOnAction(e -> applicationManager.showDetailPage(film));

            Button editButton = new Button("Edit Film");
            editButton.getStyleClass().add("button-edit");
            editButton.setOnAction(e -> applicationManager.showEditFilmPage(film));

            Button deleteButton = new Button("Delete Film");
            deleteButton.getStyleClass().add("button-delete");
            deleteButton.setOnAction(e -> {
                FilmRepository.deleteFilm(film.getId());
                applicationManager.showHomePage(); // Refresh the page
            });

            filmBox.getChildren().addAll(posterImageView, filmTitleLabel, filmDirectorLabel, detailButton, editButton, deleteButton);
            filmRow.getChildren().add(filmBox);

            if (filmRow.getChildren().size() == 3) { // Adjust the number of films per row as needed
                mainLayout.getChildren().add(filmRow);
                filmRow = new HBox(10);
                filmRow.setPadding(new Insets(10));
                filmRow.getStyleClass().add("film-row");
            }
        }

        if (!filmRow.getChildren().isEmpty()) {
            mainLayout.getChildren().add(filmRow);
        }

        scene = new Scene(mainLayout,1280,720);
        scene.getStylesheets().add(getClass().getResource("/Styles.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }
}