package networklist1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Long year;
    private Long availableCopies;
    public Integer getBookId() {
        return bookId;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
    }
    public Long getYear() {
        return year;
    }
    public Long getAvailableCopies() {
        return availableCopies;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setYear(Long year) {
        this.year = year;
    }
    public void setAvailableCopies(Long availableCopies) {
        this.availableCopies = availableCopies;
    }
}

