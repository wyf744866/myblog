package com.wyf.myblog.service;

import com.wyf.myblog.domain.Comment;

import java.util.List;

/**
 * @author wyf
 * @create 2020/6/2
 */
public interface CommentService {
    /*根据blog的id查找对应的评论*/
    List<Comment> findAllCommentsByBlogId(Long id);
    /*根据评论的id查找评论*/
    Comment findById(Long id);
    /*保存评论*/
    Comment saveComment(Comment comment);
}
