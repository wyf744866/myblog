package com.wyf.myblog.web;

import com.wyf.myblog.domain.Tag;
import com.wyf.myblog.service.BlogService;
import com.wyf.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author wyf
 * @create 2020/6/4
 */
@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    //登录前分类的页面
    @GetMapping("/tags/{id}")
    public String tag(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                      @PathVariable Long id, Model model){
        /*所有的标签集合*/
        List<Tag> tags = tagService.TagList(100000);
        if (id == -1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        /*blog中的某个tag对应的全部blog分页的查询结果：多对多查询*/
        model.addAttribute("page",blogService.blogList(id,pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }

    //登录前分类的页面
    @GetMapping("/admin/tags/{id}")
    public String admin_tag(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                            @PathVariable Long id, Model model){
        /*所有的标签集合*/
        List<Tag> tags = tagService.TagList(100000);
        if (id == -1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        /*blog中的某个tag对应的全部blog分页的查询结果：多对多查询*/
        model.addAttribute("page",blogService.blogList(id,pageable));
        model.addAttribute("activeTagId",id);

        return "admin/admin_tags";
    }

}
