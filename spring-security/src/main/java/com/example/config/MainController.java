package com.example.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther yangxiaolong
 * @create 2025/8/11
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        // 主动获取认证信息并传递给视图
        model.addAttribute("isAuthenticated",
                authentication != null && authentication.isAuthenticated());
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("username", authentication.getName());
        }
        return "home";
    }

    @GetMapping("/home")
    public String index(Model model, Authentication authentication) {
        // 复用上面的逻辑
        return home(model, authentication);
    }

    @GetMapping("/public/message")
    @ResponseBody
    public String publicMessage() {
        return "这是公开信息，不需要登录即可访问";
    }

    @GetMapping("/user/message")
    @ResponseBody
    public String userMessage() {
        return "这是用户信息，需要USER角色才能访问";
    }

    @GetMapping("/admin/message")
    @ResponseBody
    public String adminMessage() {
        return "这是管理员信息，需要ADMIN角色才能访问";
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // 返回login.html视图
    }

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logout-success";  // 返回登出成功页面
    }

    @GetMapping("/auth-status")
    @ResponseBody
    public String authStatus(Authentication authentication) {
        if (authentication == null) {
            return "未登录";
        }
        return "已登录，用户：" + authentication.getName() + "，角色：" + authentication.getAuthorities();
    }
}