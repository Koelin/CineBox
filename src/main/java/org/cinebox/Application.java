package org.cinebox;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) {
        ApplicationManager appManager = new ApplicationManager(stage);
        appManager.showRegisterUserPage();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch();
    }
}