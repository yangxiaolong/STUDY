package jdbc.controller;

/**
 * @author yangxiaolong
 */

import jdbc.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/add")
    public String addUser(String userName, Integer age) {
        return userService.addUser(userName, age) ? "success" : "fail";
    }
}