package com.qzp.blog.controller.admin;

import com.qzp.blog.po.User;
import com.qzp.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author quezhipeng
 * @date 2020/7/7 14:57
 * @Desc 博客前端用户登录controller
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * @Description 登录页面
     * @param
     * @return {@link String}
     * @author qzp
     * @date 2020/7/8 10:50
     */
    @GetMapping("/loginPage")
    public String loginPage(){
        return "admin/login";
    }
    /**
     * @Description 登录首页
     * @param userName:用户名
     * @param passWord:密码
     * @param session:域对象
     * @param attributes ：请求域对象
     * @return {@link String}
     * @author qzp
     * @date 2020/7/8 10:51
     */
    @PostMapping("/login")
    public String login(@RequestParam(name = "username") String userName,
                        @RequestParam(name = "password") String passWord,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(userName, passWord);
        if(user != null){
            user.setPassWord(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名和密码不正确");
            return "redirect:/admin/loginPage";
        }
    }
    /**
     * @Description 注销登录
     * @param session
     * @return {@link String}
     * @author qzp
     * @date 2020/7/8 10:53
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin/loginPage";
    }
}
