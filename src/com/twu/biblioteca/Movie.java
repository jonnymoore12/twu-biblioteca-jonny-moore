package com.twu.biblioteca;

/**
 * Created by JonnyM on 22/02/2017.
 */
public class Movie {

    String name;
    String director;
    String year;
    String rating;
    boolean isAvailable;

    public Movie(String name, String director, String year, String rating, boolean isAvailable) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.isAvailable = isAvailable;
    }

    public Movie(String name, String director, String year, String rating) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }
}
