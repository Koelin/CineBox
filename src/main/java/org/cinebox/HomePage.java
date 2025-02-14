package org.cinebox;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomePage {

    private Scene Scene;
    private HBox filmListHBox;

    public HomePage(ApplicationManager appManager) {

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        layout.setVgap(10);
        layout.setHgap(10);


        Label welcomeLabel = new Label("Welkom bij FilmApp!");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> appManager.showLoginPage());

        Button addFilmButton = new Button("Film Toevoegen");
        addFilmButton.setOnAction(e -> appManager.showFilmAddPage());

        filmListHBox = new HBox(10);
        filmListHBox.setPadding(new Insets(20));


        layout.add(welcomeLabel, 0, 0);
        layout.add(loginButton, 0, 1);
        layout.add(addFilmButton, 0, 2);
        layout.add(filmListHBox, 0, 3);
        Scene = new Scene(layout, 1270, 720);

    }

    public void refreshFilmList() {
        filmListHBox.getChildren().clear();
        for (Film film : filmRepository.getFilms()) {
            Label filmLabel = new Label(film.getTitle() + " - " + film.getDirector());
            filmListHBox.getChildren().add(filmLabel);
        }
    }

    public Scene getScene() {
        return Scene;
    }
}
