package org.cinebox;

public class Film {

    private int id;
    private String title;
    private String description;
    private String review;
    private String director;
    private String genre;
    private String poster;

    public Film(int id, String title, String description, String review ,String director, String genre, String poster) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.review = review;
        this.director = director;
        this.genre = genre;
        this.poster = poster;
    }

    public Film(String title, String description, String review ,String director, String genre, String poster) {
        this.title = title;
        this.description = description;
        this.review = review;
        this.director = director;
        this.genre = genre;
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

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }


    public String getPoster() {
        return poster;
    }

    public String getReview() {
        return review;
    }
}
