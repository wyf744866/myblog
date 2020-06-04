package com.wyf.myblog.web;


import com.wyf.myblog.exceptionsolve.NotFoundException;
import com.wyf.myblog.service.BlogService;
import com.wyf.myblog.service.CommentService;
import com.wyf.myblog.service.TagService;
import com.wyf.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * @author wyf
 * @create 2020/5/24
 */

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private CommentService commentService;

    //登录前：前端的初始化界面
    @GetMapping("/")
    public String index() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        //左端：把博客内容查询分页传到前端
        model.addAttribute("page", blogService.blogListIndex(pageable));
        //右端：
        //1.标签内容,参数为：展示的个数，按照所属博客数从高到底
        model.addAttribute("tags", tagService.TagList(10));
        //2.分类内容
        model.addAttribute("types", typeService.TypeList(6));
        //3.最新推荐部分：推荐博客的按照浏览量排序5条
        model.addAttribute("blogs", blogService.blogList(5));

        return "index";
    }

    /*登录前：博客详情浏览页面*/
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        //左端：
        //1.博客页面
        model.addAttribute("blog", blogService.findBlogAndConvert(id));
        //2.博客底下的评论
        model.addAttribute("comments", commentService.findAllCommentsByBlogId(id));
        //右端：
        //1.标签内容,参数为：展示的个数，按照所属博客数从高到底
        model.addAttribute("tags", tagService.TagList(10));
        //2.分类内容
        model.addAttribute("types", typeService.TypeList(6));
        //3.最新推荐部分：推荐博客的按照浏览量排序5条
        model.addAttribute("blogs", blogService.blogList(5));

        return "blog";
    }

    /*登录前：搜索功能*/
    @PostMapping("/search")
    public String search(@PageableDefault(size = 5, sort = {"view"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String search, Model model) {

        /*模糊查询，根据目录和内容的关键字*/
        model.addAttribute("page", blogService.blogSearch("%" + search + "%", pageable));
        /*在搜索页面的搜索框显示自己输入的内容*/
        model.addAttribute("search", search);
        return "search";
    }


    /*登录后：前端展示的博客分页首页*/
    @GetMapping("/admin/index")
    public String admin_index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                              Model model) {
        //左端：把博客内容查询分页传到前端
        model.addAttribute("page", blogService.blogListIndex(pageable));
        //右端：
        //1.标签内容,参数为：展示的个数，按照所属博客数从高到底
        model.addAttribute("tags", tagService.TagList(10));
        //2.分类内容
        model.addAttribute("types", typeService.TypeList(6));
        //3.最新推荐部分：推荐博客的按照浏览量排序5条
        model.addAttribute("blogs", blogService.blogList(5));

        return "admin/admin_index";
    }

    /*博客详情浏览页面*/
    @GetMapping("/admin/blog/{id}")
    public String admin_blog(@PathVariable Long id, Model model) {
        //左端：
        //1.博客页面
        model.addAttribute("blog", blogService.findBlogAndConvert(id));
        //2.博客底下的评论
        model.addAttribute("comments", commentService.findAllCommentsByBlogId(id));
        //右端：
        //1.标签内容,参数为：展示的个数，按照所属博客数从高到底
        model.addAttribute("tags", tagService.TagList(10));
        //2.分类内容
        model.addAttribute("types", typeService.TypeList(6));
        //3.最新推荐部分：推荐博客的按照浏览量排序5条
        model.addAttribute("blogs", blogService.blogList(5));

        return "admin/admin_blog";
    }

    /*登录后：搜索功能*/
    @PostMapping("/admin/search")
    public String admin_search(@PageableDefault(size = 5, sort = {"view"}, direction = Sort.Direction.DESC) Pageable pageable,
                               @RequestParam String search, Model model) {

        /*模糊查询，根据目录和内容的关键字*/
        model.addAttribute("page", blogService.blogSearch("%" + search + "%", pageable));
        /*在搜索页面的搜索框显示自己输入的内容*/
        model.addAttribute("search", search);
        return "admin/admin_search";
    }
}

