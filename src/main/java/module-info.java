module org.example.cinebox {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.swing;


    opens org.cinebox to javafx.fxml;
    exports org.cinebox;
    exports org.cinebox.Pages;
    opens org.cinebox.Pages to javafx.fxml;
    exports org.cinebox.Repositories;
    opens org.cinebox.Repositories to javafx.fxml;
    exports org.cinebox.Classes;
    opens org.cinebox.Classes to javafx.fxml;
}