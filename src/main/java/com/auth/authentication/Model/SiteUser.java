package com.auth.authentication.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, name = "userName" , nullable = false)
    private String userName;
    @Column(name = "password" , nullable = false)
    private String password;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Posts> posts;

    public SiteUser(){

    }
    public SiteUser(String userName,String password)
    {
        this.userName=userName;
        this.password = password;
    }
    public List<Posts> getPosts() {
        return posts;
    }

    public SiteUser setPosts(List<Posts> posts) {
        this.posts = posts;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
