module org.example.cinebox {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.cinebox to javafx.fxml;
    exports org.cinebox;
}