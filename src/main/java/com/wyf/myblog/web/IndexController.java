package com.wyf.myblog.web;


import com.wyf.myblog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/*
 * @author wyf
 * @create 2020/5/24
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() throws NotFoundException {
//        int i= 9/0;
//        String blog = null;
//        if (blog == null){
//            throw new NotFoundException("找不到頁面");
//        }
//        System.out.println("-----index------");
        return "index";
    }
}

