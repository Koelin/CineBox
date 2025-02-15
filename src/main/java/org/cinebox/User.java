package org.cinebox;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private List<Film> films;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.films = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void addFilm(Film film) {
        films.add(film);
    }
}