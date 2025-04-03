package com.adarsh.app.Rest;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {

    @Id
    private String id;  // MongoDB generates a unique ID for each book
    private String title;
    private String genre;
    private String authorId;  // This is the author's ID, referencing the Author document

    // Constructors
    public Book() {}

    public Book(String title, String genre, String authorId) {
        this.title = title;
        this.genre = genre;
        this.authorId = authorId;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
