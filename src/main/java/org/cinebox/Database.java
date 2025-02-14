package org.cinebox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection conn;

    public Database() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinebox", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
