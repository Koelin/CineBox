package org.cinebox;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

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

    public Film(int id, String title, String description, String review, String director, int genreId, int userId, String poster, String genre, String rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.review = review;
        this.director = director;
        this.genreId = genreId;
        this.userId = userId;
        this.poster = new Image(poster);
        this.genre = genre;
        this.rating = rating;
    }

    public Film(String title, String description, String review, String director, int genreId, int userId, Image poster, String genre, String rating) {
        this.title = title;
        this.description = description;
        this.review = review;
        this.director = director;
        this.genreId = genreId;
        this.userId = userId;
        this.poster = poster;
        this.genre = genre;
        this.rating = rating;
    }

    public Film(int id, String title, String description, String review, String director, int genreId, int userId, byte[] posterBytes, String genre, String rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.review = review;
        this.director = director;
        this.genreId = genreId;
        this.userId = userId;
        this.poster = new Image(new ByteArrayInputStream(posterBytes));
        this.genre = genre;
        this.rating = rating;
    }

    public Film(String title, String description, String review, String director, int genreId, int userId, byte[] posterBytes, String genre, String rating) {
        this.title = title;
        this.description = description;
        this.review = review;
        this.director = director;
        this.genreId = genreId;
        this.userId = userId;
        this.poster = new Image(new ByteArrayInputStream(posterBytes));
        this.genre = genre;
        this.rating = rating;
    }


    public Film(String title, String description, String director, int genreId, int userId, byte[] posterBytes, String genre) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.genreId = genreId;
        this.userId = userId;
        this.poster = new Image(new ByteArrayInputStream(posterBytes));
        this.genre = genre;
    }

    public Film(int id, String title, String description, String director, int genreId, int userId, byte[] posterBytes, String genre) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.director = director;
        this.genreId = genreId;
        this.userId = userId;
        this.genre = genre;
        if (posterBytes != null) {
            this.poster = new Image(new ByteArrayInputStream(posterBytes));
        } else {
            this.poster = null;
        }
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

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPoster(Image poster) {
        this.poster = poster;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public byte[] getPosterBytes() {
        return null;
    }
}