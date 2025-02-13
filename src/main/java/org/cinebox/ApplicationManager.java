package org.cinebox;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ApplicationManager {


    public ApplicationManager() {
    }

    public void run(Stage stage) {

        Pane root = new Pane();

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("CineBox");
        stage.setScene(scene);
        stage.show();


    }
}
