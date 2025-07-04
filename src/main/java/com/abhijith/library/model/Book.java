package com.abhijith.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Author must not be blank")
    private String author;

    @Min(value = 1000, message = "Published year must be a 4-digit year")
    @Max(value = 9999, message = "Published year must be a 4-digit year")
    private Integer publishedyr;

    @NotBlank(message = "Summary should not be empty")
    @Size(max = 500, message = "Summary should not exceed 500 characters")
    private String summary;

    @Min(value = 1, message = "Price must be greater than 0")
    private Double price;

    public Book() {}

    public Book(String title, String author, Integer publishedyr, String summary, Double price) {
        this.title = title;
        this.author = author;
        this.publishedyr = publishedyr;
        this.summary = summary;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Integer getPublishedyr() { return publishedyr; }
    public void setPublishedyr(Integer publishedyr) { this.publishedyr = publishedyr; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
