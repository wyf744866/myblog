package com.wyf.myblog.web;

import com.wyf.myblog.domain.Comment;
import com.wyf.myblog.domain.User;
import com.wyf.myblog.service.BlogService;
import com.wyf.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author wyf
 * @create 2020/6/4
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    //提交评论（保存到数据库）
    @PostMapping("/admin/blogs/comment")
    public String comment_do(Comment comment, HttpSession session) {

        /*设置对应回复显示的用户头像，设置评论昵称*/
        User user = (User) session.getAttribute("user");
        comment.setAvatar(user.getAvatar());
        comment.setNickname(user.getNikename());

        /*前端的参数会封装为comment对象，自动创建它里面对应的属性：这里的ParentComments只有id，所以要重新保存父评论*/
        /*保存的评论的父评论值设置*/
        Long parentId = comment.getParentComment().getId();
        //如果存在父评论
        if (parentId != -1){
            comment.setParentComment(commentService.findById(parentId));
        } else {
            //数据库重置保存父评论设置为null，对应前端保存的-1，防止报错
            comment.setParentComment(null);
        }
        /*同理还要保存对应的Blog对象具体内容*/
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.findById(blogId));

        //判断是否为博主.getUsername()
        if (user.getUsername().equals(comment.getBlog().getUser().getUsername())){
            comment.setAdminComment(true);
        }else {
            comment.setAdminComment(false);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+blogId;
    }

    /*ajax请求更新评论列表*/
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId , Model model) {
        /*根据博客返回博客下的评论内容*/
        model.addAttribute("comments",commentService.findAllCommentsByBlogId(blogId));
        return "admin/admin_blog :: commentList";
    }
}
