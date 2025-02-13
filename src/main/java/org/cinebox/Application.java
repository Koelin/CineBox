package org.cinebox;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

//        Pane root = new Pane();
//
//        Scene scene = new Scene(root, 320, 240);
//        stage.setTitle("CineBox");
//        stage.setScene(scene);
//        stage.show();


        ApplicationManager appManager = new ApplicationManager();
        appManager.run(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}