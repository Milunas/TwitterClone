package milunas.twitt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Twitt {

    @Id
    @GeneratedValue
    private long id;
    private final String author;
    private final Date creationDate;
    private final String message;

    public Twitt(String author, Date creationDate, String message){
        this.author = author;
        this.creationDate = creationDate;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getMessage() {
        return message;
    }
}
