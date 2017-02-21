package com.twu.biblioteca;

/**
 * Created by JonnyM on 20/02/2017.
 */
public class Book {

    private String title;
    private String author;
    private String year;
    public boolean isAvailable = true;

    public Book(String title, String author, String year, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = isAvailable;
    }

    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
