package org.cinebox.Repositories;

import org.cinebox.Classes.Film;
import org.cinebox.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilmRepositoryTest {

    @Test
    void addFilm() {

        Film film = new Film("Test Title", "Test Description", "Test Director", 1, 1, new byte[0], "Action", "Test Review", 5);
        FilmRepository.addFilm(film);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Film WHERE title = ?")) {
            stmt.setString(1, "Test Title");
            ResultSet rs = stmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("Test Title", rs.getString("title"));
            assertEquals("Test Description", rs.getString("description"));
            assertEquals("Test Director", rs.getString("director"));
            assertEquals(1, rs.getInt("genre_id"));
            assertEquals(1, rs.getInt("user_id"));
            assertEquals(5, rs.getInt("rating"));
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database query failed");
        }

    }

    @Test
    void getFilmsByUser() {

        Film film1 = new Film("Test Title 1", "Test Description 1", "Test Director 1", 1, 1, new byte[0], "Action", "Test Review 1", 5);
        Film film2 = new Film("Test Title 2", "Test Description 2", "Test Director 2", 1, 1, new byte[0], "Action", "Test Review 2", 4);
        FilmRepository.addFilm(film1);
        FilmRepository.addFilm(film2);

        List<Film> films = FilmRepository.getFilmsByUser(1);
        assertEquals(2, films.size());
        assertEquals("Test Title 1", films.get(0).getTitle());
        assertEquals("Test Title 2", films.get(1).getTitle());
    }

    @Test
    void editFilm() {
        Film film = new Film("Test Title", "Test Description", "Test Director", 1, 1, new byte[0], "Action", "Test Review", 5);
        FilmRepository.addFilm(film);

        film.setTitle("Updated Title");
        film.setDescription("Updated Description");
        FilmRepository.editFilm(film);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Film WHERE title = ?")) {
            stmt.setString(1, "Updated Title");
            ResultSet rs = stmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("Updated Title", rs.getString("title"));
            assertEquals("Updated Description", rs.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database query failed");
        }
    }

    @Test
    void deleteFilm() {
        Film film = new Film("Test Title", "Test Description", "Test Director", 1, 1, new byte[0], "Action", "Test Review", 5);
        FilmRepository.addFilm(film);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Film WHERE title = ?")) {
            stmt.setString(1, "Test Title");
            ResultSet rs = stmt.executeQuery();
            assertTrue(rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database query failed");
        }

        FilmRepository.deleteFilm(film.getId());

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Film WHERE title = ?")) {
            stmt.setString(1, "Test Title");
            ResultSet rs = stmt.executeQuery();
            assertFalse(rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database query failed");
        }
    }
}

