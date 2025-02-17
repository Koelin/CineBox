package org.cinebox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository {
    public static void addFilm(Film film) {
        String sql = "INSERT INTO Film (title, description, director, genre_id, user_id, poster) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getDescription());
            stmt.setString(3, film.getDirector());
            stmt.setInt(4, film.getGenreId());
            stmt.setInt(5, film.getUserId());
            stmt.setBytes(6, film.getPosterBytes()); // Convert Image to byte array
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Film> getFilmsByUser(int userId) {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT f.id, f.title, f.description, f.director, f.genre_id, f.user_id, f.poster, g.name AS genre " +
                "FROM Film f " +
                "JOIN Genre g ON f.genre_id = g.id " +
                "WHERE f.user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                films.add(new Film(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("director"),
                        rs.getInt("genre_id"),
                        rs.getInt("user_id"),
                        rs.getBytes("poster"),
                        rs.getString("genre")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }
}