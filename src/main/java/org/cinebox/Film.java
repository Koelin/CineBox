package org.cinebox;

import javafx.scene.image.Image;

public class Film {
    private int id;
    private String title;
    private String description;
    private String review;
    private String director;
    private int genreId;
    private int userId;
    private Image poster;
    private String genre;
    private String rating;

    public Film(int id, String title, String description, String review, String director, int genreId, int userId, Image poster) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.review = review;
        this.director = director;
        this.genreId = genreId;
        this.userId = userId;
        this.poster = poster;
    }

    public Film(String title, String description, String review, String director, int genreId, int userId, Image poster) {
        this.title = title;
        this.description = description;
        this.review = review;
        this.director = director;
        this.genreId = genreId;
        this.userId = userId;
        this.poster = poster;
    }


    public Film(String title, String description, String review, String director, int genreId, int userId, Image poster, String genre, String rating) {
        this.title = title;
        this.description = description;
        this.review = review;
        this.director = director;
        this.genreId = genreId;
        this.userId = userId;
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getReview() {
        return review;
    }

    public String getDirector() {
        return director;
    }

    public int getGenreId() {
        return genreId;
    }

    public int getUserId() {
        return userId;
    }

    public Image getPoster() {
        return poster;
    }

    public String getGenre() {
        return genre;
    }

    public String getRating() {
        return rating;
    }


}