module org.example.cinebox {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.swing;


    opens org.cinebox to javafx.fxml;
    exports org.cinebox;
}