package org.cinebox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository {

    // Get all films from the database
    public static void addFilm(Film film) {
        String sql = "INSERT INTO Film (title, description, director, genre_id, user_id, poster, rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getDescription());
            stmt.setString(3, film.getDirector());
            stmt.setInt(4, film.getGenreId());
            stmt.setInt(5, film.getUserId());
            stmt.setBytes(6, film.getPosterBytes());
            stmt.setInt(7, film.getRating());
            stmt.executeUpdate();

            // Insert review into the Review table
            String reviewSql = "INSERT INTO Review (film_id, review) VALUES (?, ?)";
            try (PreparedStatement reviewStmt = conn.prepareStatement(reviewSql)) {
                reviewStmt.setInt(1, getLastInsertedFilmId(conn));
                reviewStmt.setString(2, film.getReview());
                reviewStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // Get the ID of the last inserted film
    private static int getLastInsertedFilmId(Connection conn) throws SQLException {
        String sql = "SELECT LAST_INSERT_ID()";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve last inserted film ID.");
            }
        }
    }

    // Get all films from the database from the user
    public static List<Film> getFilmsByUser(int userId) {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT f.id, f.title, f.description, f.director, f.genre_id, f.user_id, f.poster, g.name AS genre, r.review, f.rating " +
                "FROM Film f " +
                "JOIN Genre g ON f.genre_id = g.id " +
                "LEFT JOIN Review r ON f.id = r.film_id " +
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
                        rs.getString("genre"),
                        rs.getString("review"),
                        rs.getInt("rating")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }


    // edit the film
    public static void editFilm(Film film) {
        String updateFilmSql = "UPDATE Film SET title = ?, description = ?, director = ?, genre_id = ?, poster = ?, rating = ? WHERE id = ?";
        String updateReviewSql = "UPDATE Review SET review = ? WHERE film_id = ?";


        try (Connection conn = DatabaseConnection.getConnection()) {
            // Update the film details
            try (PreparedStatement stmt = conn.prepareStatement(updateFilmSql)) {
                stmt.setString(1, film.getTitle());
                stmt.setString(2, film.getDescription());
                stmt.setString(3, film.getDirector());
                stmt.setInt(4, film.getGenreId());
                stmt.setBytes(5, film.getPosterBytes());
                stmt.setInt(6, film.getRating());
                stmt.setInt(7, film.getId());
                stmt.executeUpdate();
            }

            // Update the review
            try (PreparedStatement stmt = conn.prepareStatement(updateReviewSql)) {
                stmt.setString(1, film.getReview());
                stmt.setInt(2, film.getId());
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteFilm(int filmId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Delete associated reviews
            String deleteReviewsQuery = "DELETE FROM review WHERE film_id = ?";
            try (PreparedStatement deleteReviewsStmt = connection.prepareStatement(deleteReviewsQuery)) {
                deleteReviewsStmt.setInt(1, filmId);
                deleteReviewsStmt.executeUpdate();
            }

            // Delete the film
            String deleteFilmQuery = "DELETE FROM film WHERE id = ?";
            try (PreparedStatement deleteFilmStmt = connection.prepareStatement(deleteFilmQuery)) {
                deleteFilmStmt.setInt(1, filmId);
                deleteFilmStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



