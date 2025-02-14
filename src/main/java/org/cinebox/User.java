package org.cinebox;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
    private List<Film> films;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.films = new ArrayList<>();
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
