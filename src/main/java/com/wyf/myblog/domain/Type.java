package com.wyf.myblog.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wyf
 * @create 2020/5/27
 */
/*类型类*/
@Entity
@Table(name = "t_type") //更改默认生成表的名字
public class Type {
    /*主键自增*/
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();
    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
