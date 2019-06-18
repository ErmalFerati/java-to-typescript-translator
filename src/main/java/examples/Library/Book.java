package examples.Library;

import annotations.Import;
import annotations.TypescriptClass;

import java.util.Date;

@TypescriptClass
public class Book {

    private long id;

    @Import
    private Author author;

    private Date publishDate;

    @Import
    private BookStats bookStats;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public BookStats getBookStats() {
        return bookStats;
    }

    public void setBookStats(BookStats bookStats) {
        this.bookStats = bookStats;
    }
}

@TypescriptClass
class BookStats {

    private long id;
    private long sales;
    private short rating;
}
