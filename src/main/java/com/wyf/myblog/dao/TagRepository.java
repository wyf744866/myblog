package com.wyf.myblog.dao;

import com.wyf.myblog.domain.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wyf
 * @create 2020/5/30
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
    /*jpa接口查找方法的时候只有byid*/
    Tag findByName(String name);

    /*自定义方法，根据tag里面的List<blog>.size来排序,用pageable来实现排序*/
    @Query("select t from Tag t")
    List<Tag> findAllByBlogSize(Pageable pageable);

}
