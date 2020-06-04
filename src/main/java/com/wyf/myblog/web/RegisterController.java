package com.wyf.myblog.web;

import com.wyf.myblog.service.UserService;
import com.wyf.myblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;


/**
 * @author wyf
 * @create 2020/6/4
 */
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    //注册
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    //处理注册
    @PostMapping("/register_do")
    public String register(@RequestParam String username,@RequestParam String nikename, @RequestParam String password,
                           @RequestParam String identityPassword, RedirectAttributes attributes) {

        /*先判断二次的密码输入是否一致*/
        if (!password.equals(identityPassword)){
            attributes.addFlashAttribute("errormessage","二次的密码输入不一致，请确认~");
            return "redirect:/register";
        }
        int id = new Random().nextInt(1000)+1;
        /*对密码进行MD5加密，在服务端存储的密码为加密后的密码*/
        boolean result = userService.saveUser(username,nikename, MD5Utils.code(password),"https://picsum.photos/id/" + id + "/100");
        if (result){
            /*存放注册成功请登录消息*/
            attributes.addFlashAttribute("sucmessage","注册成功！");
            return "redirect:admin";
        }
        attributes.addFlashAttribute("errormessage","来晚一步，用户名已经被注册啦~");
        return "redirect:/register";
    }
    /*处理注册 上面和下面的方法都可以，区别在于把参数封装为user对象，不用加@RequestParam注解，但是属性名要一一对应*//*
    @PostMapping("/register_do")
    public String register(User user, RedirectAttributes attributes) {
        *//*对密码进行MD5加密，在服务端存储的密码为加密后的密码*//*
        boolean result = userService.saveUser(user.getUsername(), MD5Utils.code(user.getPassword()),"https://picsum.photos/id/1005/100/100");
        if (result){
            *//*存放注册成功请登录消息*//*
            attributes.addFlashAttribute("sucmessage","注册成功！");
            return "redirect:/login";
        }
        attributes.addFlashAttribute("message","来晚一步，用户名已经被注册啦~");
        return "redirect:/register";
    }
*/
}
