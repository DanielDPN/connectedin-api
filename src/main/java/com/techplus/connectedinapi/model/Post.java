package com.techplus.connectedinapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techplus.connectedinapi.enums.PostStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post implements Comparable<Post> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(nullable = false)
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    @Column(nullable = false)
    private PostStatus status;
    @Transient
    private byte[] pic;
    @Transient
    private List<Like> likes;
    @Transient
    private boolean liked;

    public Post() {
    }

    public Post(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Post(String title, String text, Date date, User owner) {
        this.title = title;
        this.text = text;
        this.date = date;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getOwner() {
        owner.setPassword("");
        owner.setRoles(new ArrayList<>());
        owner.setPosts(new ArrayList<>());
        owner.setContacts(new ArrayList<>());
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public int compareTo(Post o) {
        return o.getDate().compareTo(getDate());
    }

}
