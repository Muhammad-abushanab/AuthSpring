package com.auth.authentication.Model;


import javax.persistence.*;

@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "content")
    private String textContent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;

    public Posts(){

    }
    public Posts(String content,SiteUser user ){
        this.textContent= content;
        this.user = user;
    }
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public SiteUser getUser() {
        return user;
    }

    public void setUser(SiteUser user) {
        this.user = user;
    }
}
