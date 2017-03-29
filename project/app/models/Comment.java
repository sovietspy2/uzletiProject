package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import play.db.jpa.Model;

@Entity
public class Comment extends Model {

	public String author;
    public Date postedAt;
     
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    public String content;
    
    @ManyToOne
    public Post post;
    
    public Comment(Post post, String author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
        this.postedAt = new Date();
    }
    
}
