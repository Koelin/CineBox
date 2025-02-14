package org.cinebox;

import java.util.ArrayList;

public class filmRepository {
    static ArrayList<Film> films = new ArrayList<Film>();



    public static void addFilm(Film film) {

        films.add(film);


    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public Film getFilm(int id) {
        for (Film film : films) {
            if (film.getId() == id) {
                return film;
            }
        }
        return null;
    }


}
