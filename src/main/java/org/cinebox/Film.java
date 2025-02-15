package org.cinebox;

public class Film {

    private int id;
    private String title;
    private String description;
    private String review;
    private String director;
    private int genreId;
    private int userId;
    private String poster;

    public Film(int id, String title, String description, String review, String director, int genreId, int userId, String poster) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.review = review;
        this.director = director;
        this.genreId = genreId;
        this.userId = userId;
        this.poster = poster;
    }

    public Film(String title, String description, String review, String director, int genreId, int userId, String poster) {
        this.title = title;
        this.description = description;
        this.review = review;
        this.director = director;
        this.genreId = genreId;
        this.userId = userId;
        this.poster = poster;
    }

    public Film(String title, String description, String review, String director, String poster) {
        this.title = title;
        this.description = description;
        this.review = review;
        this.director = director;
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

    public String getPoster() {
        return poster;
    }
}