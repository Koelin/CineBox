package org.cinebox;

import java.util.ArrayList;

public class filmRepository {
    static ArrayList<Film> films = new ArrayList<Film>();



    public static void addFilm(Film film) {

        films.add(film);


    }

    public static ArrayList<Film> getFilms() {
        return films;
    }



}
