package com.wyf.myblog.web;

import com.wyf.myblog.domain.User;
import com.wyf.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author wyf
 * @create 2020/6/4
 */
@Controller
public class ArchivesController {
    @Autowired
    private BlogService blogService;

    /*登录前:显示所有用户的博客归档*/
    @GetMapping("/archives")
    public String archive(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogsNum",blogService.countBlog());
        return "archives";
    }


    /*登录后：
    1.显示全站的博客归档*/
    @GetMapping("/admin/archives")
    public String admin_archive(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogsNum",blogService.countBlog());
        return "admin/admin_archives1";
    }
    //2.显示自己的博客归档
    @GetMapping("/admin/archives/2")
    public String admin_MyArchive(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        model.addAttribute("archiveMap",blogService.archiveBlogOne(userId));
        model.addAttribute("blogsNum",blogService.countBlogByUser(userId));
        return "admin/admin_archives2";
    }
}
