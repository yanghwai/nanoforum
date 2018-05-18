package com.inmu.nanoforum.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "uid")
    private AppUser author;

    @Column(name = "posttime")
    private Timestamp postTime;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotEmpty
    @Column(name = "body")
    private String content;

    //constructors

    public Topic() {
    }

    public Topic(Timestamp postTime, String title, String content) {
        this.postTime = postTime;
        this.title = title;
        this.content = content;
    }

    // setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getAuthor() {
        return author;
    }

    public void setAuthor(AppUser author) {
        this.author = author;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // toString
    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", postTime=" + postTime +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
