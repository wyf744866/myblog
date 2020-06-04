package com.wyf.myblog.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wyf
 * @create 2020/5/27
 */
/*用户类*/
@Entity
@Table(name = "t_user")
public class User {
    /*主键*/
    @Id
    @GeneratedValue
    private Long id;
    private String nikename;
    private String username;
    private String password;
    private String email;
    /*头像*/
    private String avatar;
    /*类型：是否管理员*/
    private Integer type;
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
    public User() {
    }
    public User(String username,String nikename, String password, String avatar, Integer type) {
        this.username = username;
        this.nikename = nikename;
        this.password = password;
        this.avatar = avatar;
        this.type = type;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nikename='" + nikename + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", type=" + type +
                '}';
    }
}
