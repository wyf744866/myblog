package com.wyf.myblog.web.admin;

import com.wyf.myblog.domain.User;
import com.wyf.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author wyf
 * @create 2020/5/27
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public String loginPage() {
        return "/admin/login";
    }

    /*业务处理*/
    /*处理登录，服务端采用MD5加密存储密码*/
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes) {
        User user = userService.checkUser(username,password);
        if (user != null){
            /*防止把密码传输过去*/
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/admin_center";
        }
        attributes.addFlashAttribute("errormessage","用户名或者密码错误!");
        return "redirect:/admin";//通过loginPage直接跳转到登陆页面
    }

    /*登出*/
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
