package org.cinebox;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {


        ApplicationManager appManager = new ApplicationManager(stage);
        appManager.showHomePage();

    }

    public static void main(String[] args) {
        launch();
    }
}