package com.digicert.coding.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Objects;

@Entity
@Table(name="book_info")
public class BookModel {
    @Id
    @NotBlank(message = "Invalid ISBN provided")
    private String ISBN;
    @NotBlank(message = "Title can not be blank")
    private String title;
    @Lob
    @Column(name="description", length = 5000)
    private String description;
    @NotBlank(message = "Author can not be blank")
    private String author;
    @NotBlank(message = "Genre can not be blank")
    private String genre;
    @NotBlank(message = "publishYear can not be blank")
    private String publishYear;
    @PositiveOrZero(message = "Negative cost not allowed")
    private double cost;

    public BookModel() {
    }

    public BookModel(String ISBN, String title, String description, String author, String genre, String publishYear, double cost) {
        this.ISBN = ISBN;
        this.title = title;
        this.description = description;
        this.author = author;
        this.genre = genre;
        this.publishYear = publishYear;
        this.cost = cost;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookModel bookModel = (BookModel) o;

        if (Double.compare(bookModel.cost, cost) != 0) return false;
        if (!Objects.equals(ISBN, bookModel.ISBN)) return false;
        if (!Objects.equals(title, bookModel.title)) return false;
        if (!Objects.equals(description, bookModel.description))
            return false;
        if (!Objects.equals(author, bookModel.author)) return false;
        if (!Objects.equals(genre, bookModel.genre)) return false;
        return Objects.equals(publishYear, bookModel.publishYear);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = ISBN != null ? ISBN.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (publishYear != null ? publishYear.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publishYear='" + publishYear + '\'' +
                ", cost=" + cost +
                '}';
    }
}
