package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import play.db.jpa.Model;

@Entity
public class Post extends Model {

	public String title;
    public Date postedAt;
    
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    public String content;
    
    @ManyToOne
    public User author;
    public Boolean isActivePost;
    
    @OneToMany(mappedBy="post", cascade=CascadeType.ALL)
    public List<Comment> comments;
    
    public Post(User author, String title, String content) {
    	this.comments = new ArrayList<Comment>();
        this.author = author;
        this.title = title;
        this.content = content;
        this.postedAt = new Date();
        isActivePost = false;
    }
    
    public Post addComment(String author, String content) {
        Comment newComment = new Comment(this, author, content).save();
        this.comments.add(newComment);
        this.save();
        return this;
    }
	
}
